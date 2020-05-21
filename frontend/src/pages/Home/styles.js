import styled from 'styled-components';

export const Container = styled.div`
  margin-bottom: 32px;
`;

export const Content = styled.div`
  margin: 0 auto;
  max-width: 1120px;

  display: flex;
  flex-direction: column;
  align-items: center;
`;

export const Card = styled.div`
  margin-top: -150px;

  width: auto;

  background: #fff;

  border-radius: 4px;

  box-shadow: 0px 0px 10px 0px rgba(0, 0, 0, 0.5);
`;

export const CardHeader = styled.div`
  display: flex;
`;

export const EmissionButton = styled.div`
  height: 72px;
  display: flex;
  align-items: center;
  justify-content: center;

  flex: 1;

  cursor: pointer;

  &:hover {
    opacity: ${props => (props.active ? 'none' : '0.8')};
  }

  background: ${props => (props.active ? '#fff' : '#e4e4e4')};
  border-radius: ${props => (props.active ? '4px' : '4px 0 4px 0')};

  span {
    color: ${props => (props.active ? '#006699' : '#333')};

    margin-left: 8px;
  }

  svg {
    color: ${props => (props.active ? '#006699' : '#333')};
  }
`;

export const AuthenticationButton = styled.div`
  height: 72px;
  display: flex;
  align-items: center;
  justify-content: center;

  flex: 1;

  cursor: pointer;

  &:hover {
    opacity: ${props => (props.active ? 'none' : '0.8')};
  }

  background: ${props => (props.active ? '#fff' : '#e4e4e4')};
  border-radius: ${props => (props.active ? '4px' : '0 4px 0 4px')};

  span {
    color: ${props => (props.active ? '#006699' : '#333')};

    margin-left: 8px;
  }

  svg {
    color: ${props => (props.active ? '#006699' : '#333')};
  }
`;

export const CardBody = styled.div`
  padding: 32px;
`;

export const Form = styled.form`
  display: flex;
  flex-direction: column;
  align-items: center;
`;
