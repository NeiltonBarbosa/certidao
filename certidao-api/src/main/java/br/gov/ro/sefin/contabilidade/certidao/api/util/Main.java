package br.gov.ro.sefin.contabilidade.certidao.api.util;

import com.softwareag.entirex.aci.BrokerException;

import br.gov.ro.sefin.contabilidade.certidao.api.service.natural.N55172AC;

public class Main {

	public static void main(String[] args) throws BrokerException {
		N55172AC n55172ac = new N55172AC();
		n55172ac.setCodigoControle("3C70D-88890");
		
		n55172ac.submit();
		
		System.out.println(n55172ac.getEncontrado());
		System.out.println(n55172ac.getCpfCnpj());
		System.out.println(n55172ac.getStatus());
		System.out.println(n55172ac.getTipo());

	}

}
