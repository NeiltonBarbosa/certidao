import { toast } from 'react-toastify';

import history from './history';

export function handle(err) {
  let message =
    'Não conseguimos processar sua solicitação. Por favor tente novamente.';

  if (err.status === 500) {
    history.push('error');
    return;
  }
  if (err.status === 404) {
    message =
      'Não conseguimos encontrar o recurso solicitado. Verique-o e tente novamente.';
  } else if (err.status === 400) {
    try {
      const { data } = err;
      message = data[0].message;
    } catch (error) {} //eslint-disable-line
  }

  toast.error(message);
}
