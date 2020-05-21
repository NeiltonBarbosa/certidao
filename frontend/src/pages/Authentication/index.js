import React, { useState, useRef } from 'react';
import { useFormik } from 'formik';
import * as Yup from 'yup';
import InputMask from 'react-input-mask';
import ReCAPTCHA from 'react-google-recaptcha';
import { toast } from 'react-toastify';
import { parse, parseISO, format, formatISO } from 'date-fns';
import Loader from 'react-loader-spinner';

import api from '~/services/api';
import * as ErrorService from '~/services/error';

import { Form } from './styles';

import Autenticada from '~/components/Autenticada';
import Modal from '~/components/Modal';
import RadioButton from '~/components/RadioButton';
import InputContainer from '~/styles/components/InputContainer';
import InputLabel from '~/styles/components/InputLabel';
import InputText from '~/styles/components/InputText';
import Button from '~/styles/components/Button';
import FeedbackError from '~/styles/components/FeedbackError';

const tipos = [
  { label: 'Diárias', value: 'DIARIAS', checked: true },
  { label: 'Diversos Responsáveis', value: 'DIV_RESP' },
  { label: 'Suprimento de Fundos', value: 'SUP_FUNDOS' },
  { label: 'Convênios', value: 'CONVENIOS' },
];

const validationSchema = Yup.object().shape({
  tipo: Yup.string().required('Tipo obrigatório'),
  cpfCnpj: Yup.string().required('CPF/CNPJ obrigatório'),
  codigoControle: Yup.string().required('Código de controle obrigatório'),
  dataHoraEmissao: Yup.string().required('Data/Hora emissão obrigatória'),
});

const initialValues = {
  tipo: 'DIARIAS',
  cpfCnpj: '',
  codigoControle: '',
  dataHoraEmissao: '',
};

export default function Authentication() {
  const recaptcha = useRef();

  const [loading, setLoading] = useState(false);
  const [modal, setModal] = useState(false);
  const [mask, setMask] = useState('999.999.999-99');
  const [certidao, setCertidao] = useState(null);

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
      setCertidao(null);

      if (!recaptcha.current.getValue()) {
        toast.error('Clique em Não sou um robô');
        setLoading(false);
        return;
      }

      const date = parse(value.dataHoraEmissao, 'dd/MM/yyyy HH:mm', new Date());
      const obj = {
        ...value,
        dataHoraEmissao: formatISO(date),
      };

      await api
        .post('?autenticar', obj, {
          params: {
            'g-recaptcha-response': recaptcha.current.getValue(),
          },
        })
        .then(response => {
          const data = {
            ...response.data,
            dataEmissaoFormatada: format(
              parseISO(response.data.dataHoraEmissao),
              'dd/MM/yyyy HH:mm'
            ),
            dataValidadeFormatada: format(
              parseISO(response.data.dataValidade),
              'dd/MM/yyyy'
            ),
          };

          setCertidao(data);
          setModal(true);
          setLoading(false);

          recaptcha.current.reset();
        })
        .catch(error => {
          setLoading(false);
          recaptcha.current.reset();
          ErrorService.handle(error.response);
        });
    },
  });

  const tipo = getFieldProps('tipo');
  const cpfCnpj = getFieldProps('cpfCnpj');
  const codigoControle = getFieldProps('codigoControle');
  const dataHoraEmissao = getFieldProps('dataHoraEmissao');

  function handleSelectType(type) {
    setFieldValue('cpfCnpj', '');

    if (type === 'CONVENIOS') {
      document.querySelector("label[for='cpfCnpj']").innerHTML = 'CNPJ *';

      setMask('99.999.999/9999-99');
    } else {
      document.querySelector("label[for='cpfCnpj']").innerHTML = 'CPF *';

      setMask('999.999.999-99');
    }
  }

  return (
    <>
      <Form onSubmit={handleSubmit}>
        <InputContainer>
          <InputLabel>TIPO *</InputLabel>
          <RadioButton
            {...tipo}
            options={tipos}
            onChange={e => {
              tipo.onChange(e);
              handleSelectType(e.target.value);
            }}
          />
        </InputContainer>

        <InputContainer>
          <InputLabel htmlFor="cpfCnpj">CPF *</InputLabel>
          <InputMask {...cpfCnpj} mask={mask} maskPlaceholder={null}>
            <InputText type="text" />
          </InputMask>
          {touched.cpfCnpj && errors.cpfCnpj && (
            <FeedbackError>{errors.cpfCnpj}</FeedbackError>
          )}
        </InputContainer>

        <InputContainer>
          <InputLabel>CÓDIGO DE CONTROLE *</InputLabel>
          <InputMask
            {...codigoControle}
            mask="*****-*****"
            maskPlaceholder={null}
          >
            <InputText type="text" />
          </InputMask>
          {touched.codigoControle && errors.codigoControle && (
            <FeedbackError>{errors.codigoControle}</FeedbackError>
          )}
        </InputContainer>

        <InputContainer>
          <InputLabel>DATA/HORA EMISSÃO *</InputLabel>
          <InputMask
            {...dataHoraEmissao}
            mask="99/99/9999 99:99"
            maskPlaceholder={null}
          >
            <InputText type="text" />
          </InputMask>
          {touched.dataHoraEmissao && errors.dataHoraEmissao && (
            <FeedbackError>{errors.dataHoraEmissao}</FeedbackError>
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
            'AUTÊNTICAR CERTIDÃO'
          )}
        </Button>
      </Form>

      {modal && (
        <Modal title="Autênticada" closeAction={setModal}>
          <Autenticada certidao={certidao} />
        </Modal>
      )}
    </>
  );
}
