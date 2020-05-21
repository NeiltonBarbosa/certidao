import styled from 'styled-components';
import media from 'styled-media-query';

export const Container = styled.div`
  display: flex;
  flex-direction: row;
  margin-top: 4px;

  ${media.lessThan('medium')`
    display: grid;
  `}
`;

export const Content = styled.label`
  display: block;
  position: relative;
  padding-left: 32px;
  margin-bottom: 12px;
  cursor: pointer;
  font-size: 16px;
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;
  color: #333;

  & + label {
    margin-left: 8px;

    ${media.lessThan('medium')`
      margin-left: 0;
    `}
  }

  input {
    position: absolute;
    opacity: 0;
    cursor: pointer;
    height: 0;
    width: 0;

    &:checked ~ span {
      background-color: #006699;
    }

    &:checked ~ span:after {
      display: block;
    }
  }

  span {
    position: absolute;
    top: -2px;
    left: 0;

    height: 24px;
    width: 24px;
    background-color: #eee;
    border-radius: 50%;

    &:after {
      content: '';
      position: absolute;
      display: none;
    }

    &:after {
      top: 8px;
      left: 8px;
      width: 8px;
      height: 8px;
      border-radius: 50%;
      background: white;
    }
  }

  &:hover input ~ span {
    background-color: #ccc;
  }
`;
