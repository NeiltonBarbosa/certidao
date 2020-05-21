import React from 'react';

import history from '~/services/history';

import close from '~/assets/close.svg';

import { Container, Content, Text, Button } from './styles';

export default function Error() {
  return (
    <Container>
      <Content>
        <img src={close} alt="" />

        <Text>
          <h1>OOOOPS...</h1>
          <p>
            Foi identificado e monitorado um problema momentâneo durante o
            atendimento desta solicitação. Pedimos que tente novamente dentro de
            minutos.
          </p>
        </Text>

        <Button onClick={() => history.go(-1)}>VOLTAR P/ PÁGINA INICIAL</Button>
      </Content>
    </Container>
  );
}
