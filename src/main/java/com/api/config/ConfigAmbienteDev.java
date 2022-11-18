package com.api.config;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.api.domain.Categoria;
import com.api.domain.Cliente;
import com.api.domain.CredencialMercadoLivre;
import com.api.domain.CredencialMercadoPago;
import com.api.domain.Endereco;
import com.api.domain.EnderecoEntrega;
import com.api.domain.Estabelecimento;
import com.api.domain.ImagemProduto;
import com.api.domain.ItemPedido;
import com.api.domain.Pagamento;
import com.api.domain.Pedido;
import com.api.domain.Produto;
import com.api.domain.Usuario;
import com.api.domain.enuns.EstatusPagamento;
import com.api.domain.enuns.EstatusPedido;
import com.api.domain.enuns.Regime;
import com.api.domain.enuns.StatusUsuario;
import com.api.domain.enuns.Roles;
import com.api.domain.enuns.Sexo;
import com.api.domain.enuns.TipoCliente;
import com.api.domain.enuns.TipoPagamento;
import com.api.domain.enuns.UF;
import com.api.repository.CategoriaRepository;
import com.api.repository.ClienteRepository;
import com.api.repository.EstabelecimentoRepository;
import com.api.repository.ImagemProdutoRepository;
import com.api.repository.PagamentoRepository;
import com.api.repository.PedidoRepository;
import com.api.repository.ProdutoRepository;
import com.api.repository.UsuarioRepository;
import com.api.services.EstabelecimentoService;
import com.api.services.PedidoService;
import com.api.services.UsuarioService;
import com.api.utils.UtilsHorasData;

@Configuration
@Profile("dev")
public class ConfigAmbienteDev {

	@Transient
	private int quantDeLoop = 10;

	@Autowired
	ProdutoRepository produtoRepository;
	@Autowired
	UsuarioRepository userRepository;
	@Autowired
	CategoriaRepository categoriaRepository;
	@Autowired
	ImagemProdutoRepository imagemProdutoRepository;
	@Autowired
	PagamentoRepository agamentoRepository;
	@Autowired
	PedidoRepository pedidoRepository;
	@Autowired
	ClienteRepository clienteRepository;
	@Autowired
	EstabelecimentoRepository estabelecimentoRepository;
	@Autowired
	PedidoService pedidoService;
	@Autowired
	UsuarioService userService;
	@Autowired
	EstabelecimentoService empresaService;

	@Bean
	public void inserindoBanco() {

//		Lista de para teste
		List<Produto> produtos = new ArrayList<>();
		List<Usuario> users = new ArrayList<>();
		List<Categoria> categorias = new ArrayList<>();
		List<Pedido> pedidos = new ArrayList<>();
		List<Estabelecimento> estabelecimentos = new ArrayList<>();
		List<Endereco> enderecos = new ArrayList<>();

// 		setando dados dos usuarios		
		Usuario user;
		Categoria categoria;
		Produto produto;
		Pedido pedido = null;

		Random gerador = new Random();
		
		Estabelecimento estabelecimento = new Estabelecimento();
		estabelecimento.setInstEstadual(null);
		estabelecimento.setInstMunicipal(null);
		estabelecimento.setRazao("Smart commerce");
		estabelecimento.setNome("Smart commerce");
		estabelecimento.setCnpjCpf("39926782027");
		estabelecimento.setLogradouro("endereço teste");
		estabelecimento.setCep("58500000");
		estabelecimento.setCidade("cidade teste");
		estabelecimento.setUf(UF.PB);
		estabelecimento.setEmail("smartcommerce@gmail.com");
		estabelecimento.setCelular1("83996386694");
		estabelecimento.setFoneFixo("8333512332");
		estabelecimento.setRegime(Regime.ME);
		estabelecimento.setStatus("S");
		estabelecimentoRepository.save(estabelecimento);

		Usuario user1 = new Usuario();
		user1.setEmail("agilityecommerce@gmail.com");
		user1.setCpf("39926782027");
		user1.setCelular("83996386694");;
		user1.setPassword("123456");
		user1.setNome("ADMIN");
		user1.setRoles("1-2-3");
		user1.setStatus(StatusUsuario.S);
//		new BCryptPasswordEncoder().encode("123456")
		user1.setCargo("R");
		user = userRepository.save(user1);

		user = new Usuario();
		user.setEmail("luanprof30@gmail.com");
		user.setPassword("123456");
		user.setNome("LUAN MIRANDA");
		user.setRoles("1-2-3-4");
//		new BCryptPasswordEncoder().encode("123456")
		user.setStatus(StatusUsuario.S);
		user.setCargo("M");
		user = userRepository.save(user);

		Cliente cliente = new Cliente();
		cliente.setUsuario(user);
		cliente.setCpfCnpj("12345678910");
		cliente.setTipo(TipoCliente.ATACADO);
		cliente.setEnderecos(null);
		cliente.setSexo(Sexo.M);
		cliente.setCelular("83996386694");

		Endereco endereco = new Endereco();
		endereco.setCep("58500-000");
		endereco.setLogradouro("rua da doidera");
		endereco.setNumero("32a");
		endereco.setCidade("São paulo");
		endereco.setBairro("Chibate");
		endereco.setUf(UF.PB);
		endereco.setPadrao("S");

		
		enderecos.add(endereco);

		cliente.setEnderecos(enderecos);

		cliente = clienteRepository.save(cliente);
		

		for (int i = 0; i < quantDeLoop; i++) {
			
			estabelecimento = new Estabelecimento();
			estabelecimento.setInstEstadual(null);
			estabelecimento.setInstMunicipal(null);
			estabelecimento.setRazao("Razão final do teste"+i);
			estabelecimento.setNome("Estabelecimento teste-"+i);
			estabelecimento.setCnpjCpf("53418110000152");
			estabelecimento.setLogradouro("endereço teste"+i);
			estabelecimento.setCep("58500000");
			estabelecimento.setCidade("cidade teste"+i);
			estabelecimento.setUf(UF.PB);
			estabelecimento.setEmail("teste@gmail.com");
			estabelecimento.setCelular1("83998365594");
			estabelecimento.setFoneFixo("8333512332");
			if(i==0) {
				estabelecimento.setMatrizId(null);
			}else if(i==1) {
				estabelecimento.setMatrizId("0");
			}else {
				estabelecimento.setMatrizId("2");
			}
			estabelecimento.setRegime(Regime.SN);
			estabelecimento.setStatus(i==1?"S":"N");
			
			estabelecimentos.add(estabelecimento);
			
			Usuario user3 = new Usuario();
			user3.setEmail("test"+i+"@gmail.com");
			user3.setCpf("39926782027");
			user3.setCelular("83996386694");;
			user3.setPassword("123456");
			user3.setNome("TESTE USER"+i);
			user3.setRoles("1-2-3");
			user3.setStatus(StatusUsuario.S);
//			new BCryptPasswordEncoder().encode("123456")
			user3.setCargo(i==1?"G":i==2?"C":"E");
			estabelecimento = new Estabelecimento();
			estabelecimento.setId(1L);
			user3.setEstabelecimento(estabelecimento);
			
			users.add(user3);
//			user = userRepository.save(user3);
			
			categoria = new Categoria(i + 1l, "categoria-test-" + (i+1), "MLB271599", "MLB442408");
			categorias.add(categoria);

			BigDecimal b = new BigDecimal(1.8);
			produto = new Produto();
			produto.setCodigoBarras("154587878" + i + 1);
			produto.setTitulo("item-" + i + 1);
			produto.setPrecoAtacado(b);
			produto.setPrecoVarejo(b.multiply(new BigDecimal(2)));
			produto.setQuantidade(i);
			produto.setPeso("1");
			produto.setComprimento("20");
			produto.setLargura("20");
			produto.setAltura("20");
			produto.setDescricao("Descrição" + i + 1);
			produto.setEstrelas(gerador.nextInt(5));
			produto.getImagens().add(new ImagemProduto(null,
					"https://a-static.mlcdn.com.br/1500x1500/relogio-binbond-de-luxo-moda-esporte-ouro-relogios-pulso-relogio-casual-cronografo-sem-genero/classicosrelogioseacessorios/crabinbondprata/b2ba92809443451bf0d1e16d28003a1c.jpg",
					"1fdfd51fdf", "nome" + i + 1, 55d));
			produto.getImagens()
					.add(new ImagemProduto(null,
							"https://images-americanas.b2w.io/produtos/01/00/img/79597/8/79597872_1GG.jpg",
							"1fdfd51fdf", "nome" + i + 1, 55d));
			produto.getImagens().add(new ImagemProduto(null,
					"https://d3ugyf2ht6aenh.cloudfront.net/stores/386/761/products/dsc08819-edit-gold11-07a7861b1e1cf702ec16186076742287-480-0.jpg",
					"1fdfd51fdf", "nome" + i + 1, 55d));
			produto.getImagens()
					.add(new ImagemProduto(null, "https://m.media-amazon.com/images/I/61QHCYJIDsL._AC_SX522_.jpg",
							"1fdfd51fdf", "nome" + i + 1, 55d));
			produto.getImagens().add(new ImagemProduto(null,
					"https://images-soubarato.b2w.io/produtos/3029006799/imagens/2020-moda-masculina-minimalista-ultra-fino-relogios-simples-aco-homens-de-negocios-inoxidavel-mesh-belt-relogio-de-quartzo-relogio-masculino/3029006799_1_large.jpg",
					"1fdfd51fdf", "nome" + i + 1, 55d));
			produto.setCategoria(categoria);
			produtos.add(produto);
			produto.setId(i + 1l);

			Pagamento pagamento = new Pagamento();
			pagamento.setNumeroDeParcelas(i + 1);

			Date date = UtilsHorasData.subtrair(new Date(), 3);

			if (i % 2 == 0) {
				pagamento.setTipo(TipoPagamento.CARTAOCREDITO);
				pagamento.setEstatus(EstatusPagamento.APROVADO);

			} else {
				pagamento.setTipo(TipoPagamento.BOLETO);
				pagamento.setEstatus(EstatusPagamento.APROVADO);
				pagamento.setDataPagamento(new Date());

				pagamento.setDataVencimento(date);

			}

			EnderecoEntrega enderecoDeEntrega = new EnderecoEntrega();
			enderecoDeEntrega.setCep("58500-000");
			enderecoDeEntrega.setLogradouro("rua da doidera");
			enderecoDeEntrega.setNumero("32a");
			enderecoDeEntrega.setCidade("São paulo");
			enderecoDeEntrega.setBairro("Chibate");
			enderecoDeEntrega.setUf(UF.PB);

			pedido = new Pedido();
			pedido.setCliente(cliente);
			pedido.setEnderecoEntrega(enderecoDeEntrega);
			pedido.setDataFechamento(date);

			if (i % 2 == 0) {
				pedido.setEstatus(EstatusPedido.FINALIZADO);
				pedido.setCodigoRastreio("QI056001109BR");

			} else {
				pedido.setEstatus(EstatusPedido.CANCELADO);
			}

			ItemPedido itens = new ItemPedido();
			itens.setPedido(pedido);
			itens.setProduto(produto);
			itens.setQuantidadeVendida(2);

			ItemPedido itens2 = new ItemPedido();
			itens2.setPedido(pedido);
			itens2.setProduto(produto);
			itens2.setQuantidadeVendida(3);

			ItemPedido itens3 = new ItemPedido();
			itens3.setPedido(pedido);
			itens3.setProduto(produto);
			itens3.setQuantidadeVendida(10);

			ArrayList<ItemPedido> itensPedido = new ArrayList<ItemPedido>();
			itensPedido.add(itens3);
			itensPedido.add(itens2);
			itensPedido.add(itens);

			pedido.setPagamento(pagamento);
			pedido.setProdutos(itensPedido);

			pedido.setValorTotal((produto.getPrecoVarejo().multiply(new BigDecimal(
					itens.getQuantidadeVendida() + itens2.getQuantidadeVendida() + itens3.getQuantidadeVendida()))));

			pedidos.add(pedido);
			

		}

// 		salvando dados		
		estabelecimentoRepository.saveAll(estabelecimentos);
		categoriaRepository.saveAll(categorias);
		produtoRepository.saveAll(produtos);
		userRepository.saveAll(users);
		pedidos.forEach(e -> pedidoService.save(e));

	}

}
