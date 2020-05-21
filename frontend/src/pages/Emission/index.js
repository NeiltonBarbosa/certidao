import React, { useRef, useState } from 'react';
import * as Yup from 'yup';
import { useFormik } from 'formik';
import InputMask from 'react-input-mask';
import ReCAPTCHA from 'react-google-recaptcha';
import { toast } from 'react-toastify';
import { format, parseISO } from 'date-fns';
import uuidv1 from 'uuid/v1';
import Loader from 'react-loader-spinner';

import api from '~/services/api';
import { formatCurrency } from '~/util/format';
import * as ErrorService from '~/services/error';

import Modal from '~/components/Modal';
import Certidao from '~/components/Certidao';
import Comunicado from '~/components/Comunicado';

import RadioButton from '~/components/RadioButton';
import InputContainer from '~/styles/components/InputContainer';
import InputLabel from '~/styles/components/InputLabel';
import InputText from '~/styles/components/InputText';
import Button from '~/styles/components/Button';
import FeedbackError from '~/styles/components/FeedbackError';

import { Form } from './styles';

const tipos = [
  { label: 'Diárias', value: 'DIARIAS', checked: true },
  { label: 'Diversos Responsáveis', value: 'DIV_RESP' },
  { label: 'Suprimento de Fundos', value: 'SUP_FUNDOS' },
  { label: 'Convênios', value: 'CONVENIOS' },
];

const validationSchema = Yup.object().shape({
  tipo: Yup.string().required('Tipo obrigatório'),
  cpfCnpj: Yup.string().required('CPF/CNPJ obrigatório'),
});

const initialValues = {
  tipo: 'DIARIAS',
  nomeRazaoSocial: '',
  cpfCnpj: '',
};

export default function Emission() {
  const recaptcha = useRef();

  const [loading, setLoading] = useState(false);
  const [mask, setMask] = useState('999.999.999-99');
  const [certidao, setCertidao] = useState(null);
  const [modal, setModal] = useState(false);

  const {
    getFieldProps,
    isValid,
    handleSubmit,
    setFieldValue,
    touched,
    errors,
  } = useFormik({
    validationSchema,
    initialValues,
    isInitialValid: false,
    onSubmit: async value => {
      setLoading(true);
      setModal(false);
      setCertidao(null);

      if (!recaptcha.current.getValue()) {
        toast.error('Clique em Não sou um robô');
        setLoading(false);
        return;
      }

      await api
        .post('/', value, {
          params: {
            'g-recaptcha-response': recaptcha.current.getValue(),
          },
        })
        .then(({ data }) => {
          const obj = {
            ...data,
            pendencias: data.pendencias.map(p => ({
              ...p,
              id: uuidv1(),
              valorFormatado: formatCurrency(p.valor),
            })),
            saldoFormatado: formatCurrency(data.saldo),
            dataEmissaoFormatada: format(
              parseISO(data.dataHoraEmissao),
              'dd/MM/yyyy HH:mm'
            ),
            dataValidadeFormatada: format(
              parseISO(data.dataValidade),
              'dd/MM/yyyy'
            ),
          };

          setCertidao(obj);
          setModal(true);
          setLoading(false);

          recaptcha.current.reset();
        })
        .catch(err => {
          setLoading(false);

          const { data, status } = err.response;

          if (status === 404) {
            try {
              const error = data[0];
              if (error.type === 'InteressadoNaoEncontradoException') {
                setModal(true);
                setCertidao(null);

                recaptcha.current.reset();
              }
            } catch (error) {} // eslint-disable-line
          } else {
            ErrorService.handle(err.response);
          }
        });
    },
  });

  const tipo = getFieldProps('tipo');
  const nomeRazaoSocial = getFieldProps('nomeRazaoSocial');
  const cpfCnpj = getFieldProps('cpfCnpj');

  function handleSelectType(type) {
    setFieldValue('nomeRazaoSocial', '');
    setFieldValue('cpfCnpj', '');

    if (type === 'CONVENIOS') {
      document.querySelector("label[for='nomeRazaoSocial']").innerHTML =
        'RAZÃO SOCIAL';
      document.querySelector("label[for='cpfCnpj']").innerHTML = 'CNPJ *';

      setMask('99.999.999/9999-99');
    } else {
      document.querySelector("label[for='nomeRazaoSocial']").innerHTML = 'NOME';
      document.querySelector("label[for='cpfCnpj']").innerHTML = 'CPF *';

      setMask('999.999.999-99');
    }
  }

  return (
    <>
      <Form onSubmit={handleSubmit}>
        <InputContainer>
          <InputLabel htmlFor="tipo">TIPO *</InputLabel>
          <RadioButton
            {...tipo}
            options={tipos}
            onChange={e => {
              tipo.onChange(e);
              handleSelectType(e.target.value);
            }}
          />
          {touched.tipo && errors.tipo && (
            <FeedbackError>{errors.tipo}</FeedbackError>
          )}
        </InputContainer>

        <InputContainer>
          <InputLabel htmlFor="nomeRazaoSocial">NOME</InputLabel>
          <InputText type="text" {...nomeRazaoSocial} />
          {touched.nome && errors.nome && (
            <FeedbackError>{errors.nome}</FeedbackError>
          )}
        </InputContainer>

        <InputContainer>
          <InputLabel htmlFor="cpfCnpj">CPF *</InputLabel>
          <InputMask mask={mask} maskPlaceholder={null} {...cpfCnpj}>
            <InputText type="text" />
          </InputMask>
          {touched.cpfCnpj && errors.cpfCnpj && (
            <FeedbackError>{errors.cpfCnpj}</FeedbackError>
          )}
        </InputContainer>

        <InputContainer>
          <ReCAPTCHA
            ref={recaptcha}
            sitekey="6LecYzsUAAAAAJoXBe--QbDSlt5TFYUZ-bKtauvv"
          />
        </InputContainer>

        <Button disabled={!isValid || loading} type="submit">
          {loading ? (
            <Loader type="Watch" color="#FFF" height={32} width={32} />
          ) : (
            'EMITIR CERTIDÃO'
          )}
        </Button>
      </Form>

      {modal && (
        <Modal
          title={
            certidao
              ? `${certidao.nomeRazaoSocial} - ${certidao.tipoDescription}`
              : 'Comunicado'
          }
          closeAction={setModal}
        >
          {certidao ? (
            <Certidao certidao={certidao} />
          ) : (
            <Comunicado cpfCnpj={cpfCnpj.value} tipo={tipo.value} />
          )}
        </Modal>
      )}
    </>
  );
}
