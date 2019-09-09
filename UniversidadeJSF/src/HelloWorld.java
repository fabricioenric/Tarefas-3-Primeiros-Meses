import javax.faces.bean.ManagedBean;

/*
* Universidade Federal do Rio Grande do Norte
* Superintendência de Informática
* Diretoria de Sistemas
* Equipe Orbitais
*
* É proibido usar, copiar, modificar, mesclar, publicar, distribuir,
sublicenciar e / ou vender cópias
* desse Software sem estar de acordo com os termos da cooperação da UFRN.
*
*
* O aviso de copyright acima deve ser incluído em todas as cópias ou partes
substanciais do Software.
*
* UniversidadeJSF
* 
* HelloWorld.java
* 31/07/2019 05:17
*/

/**
* TODO Por favor, inclua um comentário significante aqui!
*
* @author  - 
*
* @version 1.0
* @since 31/07/2019
*
*/

@ManagedBean(name = "helloWorld")
public class HelloWorld {
	public String getMessage() {
		return "Hello World!";
	}
}
