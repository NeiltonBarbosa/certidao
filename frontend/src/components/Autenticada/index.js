import React, { useRef } from 'react';
import ReactToPrint from 'react-to-print';
import { MdLocalPrintshop } from 'react-icons/md';
import PropTypes from 'prop-types';

import {
  Container,
  Header,
  Content,
  Title,
  Image,
  InfoContainer,
  Info,
  Button,
} from './styles';

import checkedFile from '~/assets/checkedfile.svg';
import rondonia from '~/assets/rondonia.png';
import logo from '~/assets/logo.svg';

export default function Autenticada({ certidao }) {
  const componentRef = useRef();

  return (
    <Container ref={componentRef}>
      <Header>
        <img src={rondonia} alt="" width={64} />

        <div>
          <span>GOVERNO DO ESTADO DE RONDÔNIA</span>
          <span>SECRETARIA DE ESTADO DE FINANÇAS</span>
          <span>SUPERINTENDÊNCIA DE CONTABILIDADE</span>
          <span>DIRETORIA CENTRAL DE CONTABILIDADE</span>
        </div>

        <img src={logo} alt="" width={64} />
      </Header>

      <Content>
        <Title>CERTIDÃO AUTÊNTICA</Title>
        <Image src={checkedFile} />

        <InfoContainer>
          <Info>
            <label>{certidao.tipo === 'CONVENIOS' ? 'CNPJ' : 'CPF'}</label>
            <span name="tipo">{certidao.cpfCnpj}</span>
          </Info>

          <Info>
            <label>CÓDIGO CONTROLE</label>
            <span name="codigoControle">{certidao.codigoControle}</span>
          </Info>

          <Info>
            <label>DATA EMISSÃO</label>
            <span>{certidao.dataEmissaoFormatada}</span>
          </Info>

          <Info>
            <label>DATA VALIDADE</label>
            <span>{certidao.dataValidadeFormatada}</span>
          </Info>
        </InfoContainer>

        <ReactToPrint
          trigger={() => (
            <Button>
              <MdLocalPrintshop size={16} />
              Imprimir
            </Button>
          )}
          content={() => componentRef.current}
        />
      </Content>
    </Container>
  );
}

Autenticada.propTypes = {
  certidao: PropTypes.shape({
    tipo: PropTypes.string,
    cpfCnpj: PropTypes.string,
    codigoControle: PropTypes.string,
    dataEmissaoFormatada: PropTypes.string,
    dataValidadeFormatada: PropTypes.string,
  }).isRequired,
};
