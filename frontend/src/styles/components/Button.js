import styled from 'styled-components';
import { darken } from 'polished';

const Button = styled.button`
  width: 100%;
  height: 50px;

  border: none;
  background: #e25822;

  color: #fff;
  font-size: 16px;
  font-weight: bold;
  border-radius: 4px;

  &:disabled {
    opacity: 0.8;
    cursor: not-allowed;
  }

  &:hover {
    background: ${darken(0.03, '#e25822')};
  }

  div {
    display: flex;
    align-items: center;
    justify-content: center;
  }
`;

export default Button;
