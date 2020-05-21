import React from 'react';
import PropTypes from 'prop-types';
import { MdClose } from 'react-icons/md';

import { Container, Header, Title, Close, Content } from './styles';

export default function Modal({ children, size, title, closeAction }) {
  return (
    <Container>
      <Content size={size}>
        <Header>
          <Title>
            <p>{title}</p>
          </Title>
          <Close onClick={() => closeAction(false)}>
            <MdClose size="18" color="#333" />
          </Close>
        </Header>
        {children}
      </Content>
    </Container>
  );
}

Modal.propTypes = {
  children: PropTypes.oneOfType([
    PropTypes.element,
    PropTypes.arrayOf(PropTypes.element),
  ]).isRequired,
  size: PropTypes.string,
  title: PropTypes.string.isRequired,
  closeAction: PropTypes.func.isRequired,
};

Modal.defaultProps = {
  size: 'default',
};
