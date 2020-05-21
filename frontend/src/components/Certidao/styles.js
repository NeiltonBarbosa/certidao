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

export const Title = styled.h3`
  margin-top: 32px;

  text-align: center;
  text-transform: uppercase;

  color: #333;
`;

export const Person = styled.div`
  display: flex;
  flex-direction: column;

  margin-top: 24px;

  p {
    font-weight: bold;
    font-size: 14px;

    color: #333;
  }
`;

export const Content = styled.div`
  margin-top: 24px;

  p {
    text-align: justify;

    margin-bottom: 16px;

    color: #333;

    font-size: 16px;

    line-height: 1.5;
  }
`;

export const Info = styled.div`
  display: flex;
  flex-direction: column;

  margin-top: 32px;

  p {
    color: #333;

    line-height: 1.5;
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

export const Table = styled.table`
  width: 100%;

  margin-bottom: 16px;

  border-collapse: collapse;

  font-size: 14px;

  td {
    border: 1px solid #000;
    padding: 4px;
  }

  thead {
    font-weight: bold;
  }
`;
