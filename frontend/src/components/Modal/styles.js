import styled from 'styled-components';

export const Container = styled.div`
  position: fixed;
  width: 100%;
  height: 100%;
  top: 0;
  left: 0;
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 99999;

  background: rgba(0, 0, 0, 0.5);
`;

export const Content = styled.div`
  width: 80%;

  max-height: 90%;
  overflow-y: scroll;

  display: block;

  border-radius: 5px;
  background: #fff;
  box-shadow: 0 2px 10px 0 rgba(0, 0, 0, 0.2);
  animation: slide 0.5s ease-out forwards;
`;

export const Header = styled.div`
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: space-between;

  border-radius: 4px;

  padding: 8px;

  background: #eee;
`;

export const Title = styled.div`
  display: flex;
  flex-direction: column;

  p {
    color: #333;

    font-size: 14px;
  }
`;

export const Close = styled.button`
  padding: 5px;
  background: none;
  border: none;
  border-radius: 50%;

  svg {
    vertical-align: middle;
  }

  &:hover {
    background: rgba(0, 0, 0, 0.1);
  }
`;
