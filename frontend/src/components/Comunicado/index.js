import React from 'react';
import PropTypes from 'prop-types';

import rondonia from '~/assets/rondonia.png';
import logo from '~/assets/logo.svg';

import { Container, Header, Content } from './styles';

export default function Comunicado({ cpfCnpj, tipo }) {
  return (
    <Container>
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
        <p>
          Em atendimento à solicitação a esta Superintendência de Contabilidade
          e em cumprimento ao preceito constitucional insculpido no inciso
          XXXIII do artigo 5º da Carta Magna, regulado pela Lei Federal n.
          12.527/2011, comunicamos que não temos informações suficientes em
          nossa base de dados para o {tipo === 'CONVENIOS' ? 'CNPJ' : 'CPF'} Nº{' '}
          <b>{cpfCnpj}</b>, impossibilitando assim a emissão da certidão
          negativa solicitada.
        </p>

        <p>
          Assim, o interessado deverá formalizar o pedido diretamente à
          Superitendência de Contabilidade através do Sistema Eletrônico de
          Informações - SEI, no endereço{' '}
          <a href="https://www.sei.ro.gov.br">https://www.sei.ro.gov.br</a> .
        </p>
      </Content>
    </Container>
  );
}

Comunicado.propTypes = {
  cpfCnpj: PropTypes.string.isRequired,
  tipo: PropTypes.string.isRequired,
};
