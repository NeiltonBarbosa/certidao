import React from 'react';
import PropTypes from 'prop-types';

import { Container, Content } from './styles';

export default function RadioButton({ options, ...rest }) {
  return (
    <Container>
      {options.map(option => (
        <Content key={option.value}>
          {option.label}
          <input
            {...rest}
            type="radio"
            value={option.value}
            checked={rest.value === option.value}
          />
          <span />
        </Content>
      ))}
    </Container>
  );
}

RadioButton.propTypes = {
  options: PropTypes.arrayOf(
    PropTypes.shape({
      label: PropTypes.string,
      value: PropTypes.any,
    })
  ).isRequired,
};
