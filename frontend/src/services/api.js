import axios from 'axios';

const api = axios.create({
  baseURL: 'http://srvcontabil.sefin.ro.gov.br/certidao-api',
  // baseURL: 'http://localhost:8080/',
});

export default api;
