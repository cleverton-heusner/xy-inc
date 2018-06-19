package br.com.xyinc.commons;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.com.xyinc.ponto.cadastro.teste.CadastroPontoTeste;
import br.com.xyinc.ponto.proximidade.teste.PontosMaisProximosTeste;

@RunWith(Suite.class)
@SuiteClasses({ CadastroPontoTeste.class, PontosMaisProximosTeste.class })
public class ExecutorSuites {
}
