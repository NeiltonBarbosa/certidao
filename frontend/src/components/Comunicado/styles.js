import styled from 'styled-components';

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
  margin-top: 16px;

  p {
    text-align: justify;

    margin-bottom: 16px;

    color: #333;

    font-size: 16px;

    line-height: 1.5;
  }
`;
