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
import com.api.domain.ImagemProduto;
import com.api.domain.ItemPedido;
import com.api.domain.Pagamento;
import com.api.domain.Pedido;
import com.api.domain.Produto;
import com.api.domain.Usuario;
import com.api.domain.enuns.EstatusPagamento;
import com.api.domain.enuns.EstatusPedido;
import com.api.domain.enuns.EstatusUsuario;
import com.api.domain.enuns.Roles;
import com.api.domain.enuns.Sexo;
import com.api.domain.enuns.TipoCliente;
import com.api.domain.enuns.TipoPagamento;
import com.api.domain.enuns.UF;
import com.api.repository.CategoriaRepository;
import com.api.repository.ClienteRepository;
import com.api.repository.ImagemProdutoRepository;
import com.api.repository.PagamentoRepository;
import com.api.repository.PedidoRepository;
import com.api.repository.ProdutoRepository;
import com.api.repository.UsuarioRepository;
import com.api.services.EmpresaService;
import com.api.services.PedidoService;
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
	PedidoService pedidoService;
	@Autowired
	EmpresaService empresaService;

	@Bean
	public void inserindoBanco() {

//		Lista de para teste
		List<Produto> produtos = new ArrayList<>();
		List<Usuario> users = new ArrayList<>();
		List<Categoria> categorias = new ArrayList<>();
		List<Pedido> pedidos = new ArrayList<>();

// 		setando dados dos usuarios		
		Usuario user;
		Categoria categoria;
		Produto produto;
		Pedido pedido = null;

		Random gerador = new Random();

		Usuario user1 = new Usuario();
		user1.setEmail("agilityecommerce@gmail.com");
		user1.setPassword("123456");
		user1.setNome("ADMIN");
		user1.setRole(Roles.MASTER);
		user1.setStatus(EstatusUsuario.ATIVO);
//		new BCryptPasswordEncoder().encode("123456")
		users.add(user1);
		user = userRepository.save(user1);

		user = new Usuario();
		user.setEmail("luanprof30@gmail.com");
		user.setPassword("123456");
		user.setNome("LUAN MIRANDA");
		user.setRole(Roles.CLIENTE);
//		new BCryptPasswordEncoder().encode("123456")
		user.setStatus(EstatusUsuario.ATIVO);
		users.add(user);
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

		List<Endereco> enderecos = new ArrayList<Endereco>();
		enderecos.add(endereco);

		cliente.setEnderecos(enderecos);

		cliente = clienteRepository.save(cliente);

		CredencialMercadoLivre mercadoLivre = new CredencialMercadoLivre(null, "2128718904902939","V7OLfmpG4XUzYnLhYmDYnK4MO6DvNqdm","Bearer APP_USR-2128718904902939-111423-265756d7711032141c81cde7e554c4ca-669091157", null, null, null,null, "TG-61a57573779d3a001ae5c57b-669091157", "S");
		empresaService.saveCredencialMercadoLivre(mercadoLivre);
		
		CredencialMercadoPago mercadoPago = new CredencialMercadoPago(null, "TEST-c7bc4be8-8d01-4ac6-9b7e-ca99d41876b9","2128718904902939","V7OLfmpG4XUzYnLhYmDYnK4MO6DvNqdm", "TEST-2128718904902939-011402-df2ae4462726e85ccec8e03b48838f64-669091157",  "S");
		empresaService.saveCredencialMercadoPago(mercadoPago);

		for (int i = 0; i < quantDeLoop; i++) {
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
		categoriaRepository.saveAll(categorias);
		produtoRepository.saveAll(produtos);
		pedidos.forEach(e -> pedidoService.save(e));

	}

}
