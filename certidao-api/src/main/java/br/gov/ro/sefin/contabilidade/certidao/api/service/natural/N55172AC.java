package br.gov.ro.sefin.contabilidade.certidao.api.service.natural;

import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.softwareag.entirex.aci.Broker;
import com.softwareag.entirex.aci.BrokerException;
import com.softwareag.entirex.aci.RPCService;

import br.gov.ro.sefin.contabilidade.certidao.api.context.ContextProvider;
import br.gov.ro.sefin.contabilidade.certidao.api.model.Certidao;
import br.gov.ro.sefin.contabilidade.certidao.api.model.Tipo;
import br.gov.ro.sefin.contabilidade.certidao.api.property.CertidaoProperty;
import lombok.Getter;
import lombok.Setter;


/* Autênticação das Certidões */
@Getter @Setter
public class N55172AC extends RPCService {
	
	public static final String DEFAULT_BROKERID = ContextProvider.getBean(CertidaoProperty.class).getEntirex().getBrokerId();
	public static final String DEFAULT_SERVER = ContextProvider.getBean(CertidaoProperty.class).getEntirex().getServerAddress();
	public static final String DEFAULT_USERID = ContextProvider.getBean(CertidaoProperty.class).getEntirex().getUserId();

	private String tipo;

	private String cpfCnpj;

	private String nomeConsultado;

	private String nomeEncontrado;

	private String dataHoraEmissao;

	private String codigoControle;

	private String dataValidade;

	private String status;

	private String saldo;

	private String dataTransacao;

	private String horaTransacao;

	private String encontrado;
	
	public Boolean isEncontrado() {
		return this.encontrado.equals("1");
	}
	
	public Certidao getCertidao() {
		Certidao certidao = new Certidao();
		certidao.setTipo(Tipo.values()[Integer.valueOf(this.tipo) - 1]);
		certidao.setCpfCnpj(certidao.getTipo().formatar(this.cpfCnpj));
		certidao.setCodigoControle(this.codigoControle);
		certidao.setDataHoraEmissao(LocalDateTime.parse(this.dataHoraEmissao, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
		certidao.setDataValidade(LocalDate.parse(this.dataValidade, DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		
		return certidao;
	}

	private final static String N55172AC_FORMAT = // from IDL
			("MA1,0MA14,0MA45,0MA45,0MA19,0MA11,0MA10,0MA1,0MA17,0MA8," + "0MA6,0MA1,0.");

	
	public N55172AC() {
		super(new Broker(DEFAULT_BROKERID, DEFAULT_USERID), DEFAULT_SERVER, "A3312019", true);
	}
	
	public static String getStubVersion() {
		return "EntireX RPC for Java Stub Version=9.12.0.0, Patch Level=332";
	}

	public void submit() throws BrokerException {

		enterStub();
		buildHeader(N55172AC_FORMAT, "12", 178, 0, "N55172AC", true, 1110, false, false, 2040);
		if (getVersion() >= 2000) {

			if (tipo == null) {
				super.marshal.addDataA(null, 1);
			} else {
				super.marshal.addDataA(tipo, 1);
			}

			if (cpfCnpj == null) {
				super.marshal.addDataA(null, 14);
			} else {
				super.marshal.addDataA(cpfCnpj, 14);
			}

			if (nomeConsultado == null) {
				super.marshal.addDataA(null, 45);
			} else {
				super.marshal.addDataA(nomeConsultado, 45);
			}

			if (nomeEncontrado == null) {
				super.marshal.addDataA(null, 45);
			} else {
				super.marshal.addDataA(nomeEncontrado, 45);
			}

			if (dataHoraEmissao== null) {
				super.marshal.addDataA(null, 19);
			} else {
				super.marshal.addDataA(dataHoraEmissao, 19);
			}

			if (codigoControle == null) {
				super.marshal.addDataA(null, 11);
			} else {
				super.marshal.addDataA(codigoControle, 11);
			}

			if (dataValidade == null) {
				super.marshal.addDataA(null, 10);
			} else {
				super.marshal.addDataA(dataValidade, 10);
			}

			if (status == null) {
				super.marshal.addDataA(null, 1);
			} else {
				super.marshal.addDataA(status, 1);
			}

			if (saldo == null) {
				super.marshal.addDataA(null, 17);
			} else {
				super.marshal.addDataA(saldo, 17);
			}

			if (dataTransacao == null) {
				super.marshal.addDataA(null, 8);
			} else {
				super.marshal.addDataA(dataTransacao, 8);
			}

			if (horaTransacao == null) {
				super.marshal.addDataA(null, 6);
			} else {
				super.marshal.addDataA(horaTransacao, 6);
			}

			if (encontrado == null) {
				super.marshal.addDataA(null, 1);
			} else {
				super.marshal.addDataA(encontrado, 1);
			}
		} else {
			super.marshal.addDataA(tipo, 1);
			super.marshal.addDataA(cpfCnpj, 14);
			super.marshal.addDataA(nomeConsultado, 45);
			super.marshal.addDataA(nomeEncontrado, 45);
			super.marshal.addDataA(dataHoraEmissao, 19);
			super.marshal.addDataA(codigoControle, 11);
			super.marshal.addDataA(dataValidade, 10);
			super.marshal.addDataA(status, 1);
			super.marshal.addDataA(saldo, 17);
			super.marshal.addDataA(dataTransacao, 8);
			super.marshal.addDataA(horaTransacao, 6);
			super.marshal.addDataA(encontrado, 1);
		}

		callServer();

		if (getVersion() >= 2000) {
			tipo = super.marshal.getDataA(1);
			cpfCnpj = super.marshal.getDataA(14);
			nomeConsultado = super.marshal.getDataA(45);
			nomeEncontrado = super.marshal.getDataA(45);
			dataHoraEmissao = super.marshal.getDataA(19);
			codigoControle = super.marshal.getDataA(11);
			dataValidade = super.marshal.getDataA(10);
			status = super.marshal.getDataA(1);
			saldo = super.marshal.getDataA(17);
			dataTransacao = super.marshal.getDataA(8);
			horaTransacao = super.marshal.getDataA(6);
			encontrado = super.marshal.getDataA(1);
		} else {
			tipo = super.marshal.getDataA(1);
			cpfCnpj = super.marshal.getDataA(14);
			nomeConsultado = super.marshal.getDataA(45);
			nomeEncontrado = super.marshal.getDataA(45);
			dataHoraEmissao = super.marshal.getDataA(19);
			codigoControle = super.marshal.getDataA(11);
			dataValidade = super.marshal.getDataA(10);
			status = super.marshal.getDataA(1);
			saldo = super.marshal.getDataA(17);
			dataTransacao = super.marshal.getDataA(8);
			horaTransacao = super.marshal.getDataA(6);
			encontrado = super.marshal.getDataA(1);
		}
		leaveStub();

	}

	public static final Method enterStubMethod = getStubMethod("enterClientStub");
	public static final Method leaveStubMethod = getStubMethod("leaveClientStub");

	private static Method getStubMethod(String name) {
		Method method = null;
		try {
			method = RPCService.class.getMethod(name, (Class<?>[]) null);
		} catch (final Exception ex) {
		}
		return method;
	}

	private void enterStub() {
		if (enterStubMethod != null) {
			try {
				enterStubMethod.invoke(this, (Object[]) null);
			} catch (final Exception ex) {
			}
		}
	}

	private void leaveStub() {
		if (leaveStubMethod != null) {
			try {
				leaveStubMethod.invoke(this, (Object[]) null);
			} catch (final Exception ex) {
			}
		}
	}

}
