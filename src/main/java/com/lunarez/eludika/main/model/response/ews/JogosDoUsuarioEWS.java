package com.lunarez.eludika.main.model.response.ews;

import com.lunarez.eludika.main.model.request.SalvarJogoNaColecaoRequestModel;
import com.lunarez.eludika.main.model.response.GenericResponseModel;
import com.lunarez.eludika.main.model.response.UsuarioTemJogoResponseModel;
import static java.lang.System.out;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpStatusCodeException;

public class JogosDoUsuarioEWS {
    
    public UsuarioTemJogoResponseModel salvarJogoNaColecao(SalvarJogoNaColecaoRequestModel dadosDaRequisicao) 
            throws HttpStatusCodeException {
        
        /* Definição da url do serviço de criação de usuário do webservice */
        String url = "http://localhost:8080/eludika-webservice/api/jogos-do-usuario";
        
        /* Criação do objeto que lida com requisições REST */
        RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
       
        /* Criação e configuração do cabeçalho */
        HttpHeaders cabecalho = new HttpHeaders();
        cabecalho.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity requisicao = new HttpEntity<>(dadosDaRequisicao, cabecalho);
        
        ResponseEntity<UsuarioTemJogoResponseModel> resposta = restTemplate.exchange(url, 
                HttpMethod.POST, requisicao, UsuarioTemJogoResponseModel.class);
        
        out.println("Resultado da criação: " + resposta.getBody().getIdExternoUsuario());
        
        return resposta.getBody();
    }
    
    public UsuarioTemJogoResponseModel obterJogoDoUsuario(String idExternoUsuario, String idExternoJogo) 
            throws HttpStatusCodeException {

        String url = "http://localhost:8080/eludika-webservice/api/usuarios/" 
                + idExternoUsuario + "/jogos/" + idExternoJogo;
        
        RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());

        ResponseEntity<UsuarioTemJogoResponseModel> resposta = restTemplate.exchange(url, 
                HttpMethod.GET, null, UsuarioTemJogoResponseModel.class);
        
        return resposta.getBody();
    }
    
    public GenericResponseModel deletarJogoDoUsuario(String idExternoUsuario, String idExternoJogo) 
            throws HttpStatusCodeException {

        String url = "http://localhost:8080/eludika-webservice/api/usuarios/" 
                + idExternoUsuario + "/jogos/" + idExternoJogo;
        
        RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());

        ResponseEntity<GenericResponseModel> resposta = restTemplate.exchange(url, 
                HttpMethod.DELETE, null, GenericResponseModel.class);
        
        
        out.println("Conteúdo da conta: " + resposta.getBody().getStatus());
        
        return resposta.getBody();
    } 
}