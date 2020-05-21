import React, { useState } from 'react';

import Emission from '../Emission';
import Authentication from '../Authentication';

import {
  Container,
  Content,
  Card,
  CardHeader,
  CardBody,
  EmissionButton,
  AuthenticationButton,
} from './styles';

import addFile from '~/assets/addfile.svg';
import checkFile from '~/assets/checkfile.svg';

import addFileActive from '~/assets/addfileactive.svg';
import checkFileActive from '~/assets/checkfileactive.svg';

export default function Home() {
  const [emissionActive, setEmissionActive] = useState(true);
  const [authenticationActive, setAuthenticationActive] = useState(false);

  function handleEmissionActive() {
    setEmissionActive(true);
    setAuthenticationActive(false);
  }

  function handleAuthenticationActive() {
    setAuthenticationActive(true);
    setEmissionActive(false);
  }

  return (
    <Container>
      <Content>
        <Card>
          <CardHeader>
            <EmissionButton
              active={emissionActive}
              onClick={handleEmissionActive}
            >
              <img
                src={emissionActive ? addFileActive : addFile}
                width={32}
                alt=""
              />
              <span>Emissão</span>
            </EmissionButton>

            <AuthenticationButton
              active={authenticationActive}
              onClick={handleAuthenticationActive}
            >
              <img
                src={authenticationActive ? checkFileActive : checkFile}
                width={28}
                alt=""
              />
              <span>Autênticação</span>
            </AuthenticationButton>
          </CardHeader>

          <CardBody>
            {emissionActive && !authenticationActive && <Emission />}
            {authenticationActive && !emissionActive && <Authentication />}
          </CardBody>
        </Card>
      </Content>
    </Container>
  );
}
