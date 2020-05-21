import styled from 'styled-components';
import { darken } from 'polished';

export const Container = styled.div`
  font-family: Verdana, Geneva, Tahoma, sans-serif, sans-serif;
  padding: 16px 32px;
  width: 100%;
`;

export const Header = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;

  div {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;

    span {
      font-size: 14px;

      margin: 1.5px 0;

      color: #333;
    }
  }
`;

export const Content = styled.div`
  display: flex;
  flex-direction: column;

  align-items: center;

  margin: 32px 0;
`;

export const Title = styled.h3`
  margin-bottom: 32px;
`;

export const Image = styled.img`
  width: 64px;
  margin-bottom: 32px;
`;

export const InfoContainer = styled.div`
  display: flex;
`;

export const Info = styled.div`
  display: flex;
  flex-direction: column;

  padding: 0 16px;

  & + div {
    border-left: 1px solid #ccc;
  }

  label {
    color: #333;

    font-size: 12px;
    font-weight: bold;

    margin-bottom: 4px;
  }

  span {
    color: #333;
    font-size: 14px;
  }
`;

export const Button = styled.button`
  display: flex;
  align-items: center;

  height: 40px;
  padding: 8px;
  margin-top: 16px;

  background: #006699;
  border: none;
  border-radius: 4px;

  color: #fff;

  svg {
    margin-right: 8px;
  }

  &:hover {
    background: ${darken(0.05, '#006699')};
  }

  @media print {
    display: none;
  }
`;
