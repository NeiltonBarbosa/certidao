import styled from 'styled-components';

export const Container = styled.div`
  position: fixed;

  height: 100%;
  width: 100%;

  top: 0;
  left: 0;

  background: rgba(207, 48, 48, 1);
`;

export const Content = styled.div`
  margin-top: 64px;

  display: flex;
  flex-direction: column;
  align-items: center;

  img {
    width: 232px;
  }
`;

export const Text = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;

  margin-top: 32px;
  padding: 0 16px;

  h1 {
    font-size: 56px;

    color: #eee;
  }

  p {
    font-size: 24px;

    color: #eee;

    text-align: center;
  }
`;

export const Button = styled.button`
  margin-top: 24px;
  height: 50px;
  padding: 0 16px;

  background: rgba(0, 0, 0, 0.7);

  color: #eee;

  border: none;
  border-radius: 4px;

  font-weight: bold;
`;
