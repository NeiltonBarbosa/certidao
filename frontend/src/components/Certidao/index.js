import React, { useRef } from 'react';
import ReactToPrint from 'react-to-print';
import { MdLocalPrintshop } from 'react-icons/md';
import PropTypes from 'prop-types';

import rondonia from '~/assets/rondonia.png';
import logo from '~/assets/logo.svg';

import {
  Container,
  Content,
  Header,
  Title,
  Person,
  Info,
  Button,
  Table,
} from './styles';

export default function Certidao({ certidao }) {
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

      <Title>CERTIDÃO NEGATIVA DE {certidao.tipoDescription}</Title>

      <Person>
        <p>
          {certidao.tipo === 'CONVENIOS' ? 'Razão Social' : 'Nome'}:{' '}
          {certidao.nomeRazaoSocial}
        </p>
        <p>
          {certidao.tipo === 'CONVENIOS' ? 'CNPJ' : 'CPF'}: {certidao.cpfCnpj}
        </p>
      </Person>

      <Content>
        {certidao.pendencias.length > 0 ? (
          <p>
            Em atendimento à solicitação a esta Superintendência de
            Contabilidade e em cumprimento ao preceito constitucional insculpido
            no inciso XXXIII do artigo 5º da Carta Magna, regulado pela Lei
            Federal n. 12.527/2011, certificamos que <b>CONSTAM</b> as seguintes
            pendências para a{' '}
            {certidao.tipo === 'CONVENIOS' ? 'empresa' : 'pessoa'} acima
            identificada relativas a {certidao.tipoDescription}, para o momento
            em que foi realizada esta consulta:
          </p>
        ) : (
          <p>
            Em atendimento à solicitação a esta Superintendência de
            Contabilidade e em cumprimento ao preceito constitucional insculpido
            no inciso XXXIII do artigo 5º da Carta Magna, regulado pela Lei
            Federal n. 12.527/2011, certificamos que <b>NÃO CONSTAM</b>{' '}
            pendências para a{' '}
            {certidao.tipo === 'CONVENIOS' ? 'empresa' : 'pessoa'} acima
            identificada relativas a {certidao.tipoDescription}, para o momento
            em que foi realizada esta consulta.
          </p>
        )}

        {certidao.pendencias.length > 0 && (
          <Table>
            <thead>
              <tr>
                {certidao.tipo === 'CONVENIOS' && (
                  <td style={{ textAlign: 'center' }}>Convênio</td>
                )}
                <td style={{ textAlign: 'center' }}>UG</td>
                <td>Situação</td>
                <td style={{ textAlign: 'right' }}>Valor (R$)</td>
              </tr>
            </thead>

            <tbody>
              {certidao.pendencias.map(pendencia => (
                <tr key={pendencia.id}>
                  {certidao.tipo === 'CONVENIOS' && (
                    <td style={{ textAlign: 'center' }}>
                      {pendencia.convenio}
                    </td>
                  )}
                  <td style={{ textAlign: 'center' }}>{pendencia.ug}</td>
                  <td>{pendencia.situacao}</td>
                  <td style={{ textAlign: 'right' }}>
                    {pendencia.valorFormatado}
                  </td>
                </tr>
              ))}
              <tr>
                <td
                  style={{
                    textAlign: 'right',
                    border: 'none',
                    fontWeight: 'bold',
                  }}
                  colSpan={certidao.tipo === 'CONVENIOS' ? '3' : '2'}
                >
                  Total (R$)
                </td>
                <td style={{ textAlign: 'right', fontWeight: 'bold' }}>
                  {certidao.saldoFormatado}
                </td>
              </tr>
            </tbody>
          </Table>
        )}

        <p>
          Esta informação refere-se exclusivamente à consulta nos registros
          contábeis do Estado de Rondônia, constantes no Sistema Integrado de
          Administração Financeira Para Estados e Municípios - SIAFEM.
        </p>

        <p>
          Registramos que a informação é primária, íntegra, autêntica e
          atualizada, entretanto, esta Superintendência não se responsabiliza
          por pendências que, por ventura, não tenham sido lançadas oportuna e
          tempestivamente pelas unidades gestoras.
        </p>

        <p>
          A autenticação desta certidão deverá ser confirmada pelo Órgão
          Interessado na página da Secretaria de Finanças do Estado de Rondônia
          na Internet, no endereço{' '}
          <a href="http://srvcontabil.sefin.ro.gov.br/certidao">
            http://srvcontabil.sefin.ro.gov.br/certidao
          </a>
        </p>
      </Content>

      <Info>
        <p>
          Situação do {certidao.tipo === 'CONVENIOS' ? 'CNPJ' : 'CPF'} no
          SIAFEM: {certidao.situacaoDocumento}
        </p>
        <p>Emitida em: {certidao.dataEmissaoFormatada}</p>
        <p>Válida até {certidao.dataValidadeFormatada}</p>
        <p>Código de Controle Nº: {certidao.codigoControle}</p>
      </Info>

      <ReactToPrint
        trigger={() => (
          <Button>
            <MdLocalPrintshop size={16} />
            Imprimir
          </Button>
        )}
        content={() => componentRef.current}
      />
    </Container>
  );
}

Certidao.propTypes = {
  certidao: PropTypes.shape({
    tipoDescription: PropTypes.string,
    tipo: PropTypes.string,
    nomeRazaoSocial: PropTypes.string,
    cpfCnpj: PropTypes.string,
    saldoFormatado: PropTypes.string,
    dataEmissaoFormatada: PropTypes.string,
    dataValidadeFormatada: PropTypes.string,
    situacaoDocumento: PropTypes.string,
    codigoControle: PropTypes.string,
    pendencias: PropTypes.arrayOf(PropTypes.object),
  }).isRequired,
};
