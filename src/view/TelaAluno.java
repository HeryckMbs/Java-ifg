package view;

import java.text.ParseException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import model.dao.AlunoDAOImp;
import model.dao.FactoryDAO;
import model.entities.Aluno;

public class TelaAluno {

	static AlunoDAOImp AlunoDao = FactoryDAO.createAlunoDAO();

	public static Scanner menuAluno(Scanner console) throws InterruptedException, ParseException {

		int opcao = 0;
		do {
			System.out.println("\n\n");
			System.out.println("    ###   Tela: Aluno     ###");
			System.out.println("    =========================");
			System.out.println("    |     1 - Cadastrar     |");
			System.out.println("    |     2 - Listar        |");
			System.out.println("    |     3 - Alterar       |");
			System.out.println("    |     4 - Excluir       |");
			System.out.println("    |     0 - Retornar      |");
			System.out.println("    =========================");
			System.out.print("    Op��o -> ");
			opcao = console.nextInt();
			console.nextLine();

			switch (opcao) {
			case 1:
				console = cadastrar(console);
				break;
			case 2:
				console = listar(console);
				break;
			case 3:
				console = alterar(console);
				break;
			case 4:
				console = excluir(console);
				break;
			case 0:
				console = TelaPrincipal.menuPrincipal(console);
				break;
			default:
				System.out.println("Op��o inv�lida!");
				TimeUnit.SECONDS.sleep(1);
			}
		} while (opcao != 0);
		return console;
	}

	private static Scanner cadastrar(Scanner console) throws ParseException {

		Aluno c = new Aluno();

		System.out.println("\n\n");
		System.out.println("    ###   Aluno-Cadastrar ###");
		System.out.println("    =========================");

		System.out.print("    |     Nome: ");
		c.setNome(console.nextLine());
		System.out.println("    =========================");
		System.out.print("    |     Sexo: ");
		c.setSexo(console.nextLine());
		System.out.println("    =========================");
		System.out.print("    |     DATA DE NASCIMENTO(yyyy-MM-dd): ");
		c.setDt_nas(console.nextLine());
		System.out.println("    =========================");
		System.out.print("    |     NOTA: ");
		c.setNota(Integer.parseInt(console.nextLine()));
		System.out.println("    =========================");
		AlunoDao.insert(c);

		console.nextLine();
		return console;
	}

	private static Scanner listar(Scanner console) {

		List<Aluno> alunos = AlunoDao.findAll();

		System.out.println("\n\n");
		System.out.println("    ###   Aluno-Listar    ###");
		System.out.println("    =========================");
		System.out.println("    |     Id\tNome");
		for (Aluno c : alunos) {
			System.out.println("    |     " + c.getIdAluno() + "\t" + c.getNome() + "\t" + c.getSexo() + "\t"
					+ c.getDt_nas() + "\t" + c.getNota());
		}
		System.out.println("    =========================");
		console.nextLine();
		return console;
	}

	private static Scanner alterar(Scanner console) throws ParseException {

		Aluno c = new Aluno();

		System.out.println("\n\n");
		System.out.println("    ###   Aluno-Alterar   ###");
		System.out.println("    =========================");
		System.out.print("    |     Id: ");
		c.setIdAluno(console.nextInt());
		console.nextLine();

		System.out.print("    |     Nome: ");
		c.setNome(console.nextLine());
		System.out.println("    =========================");
		System.out.print("    |     Sexo: ");
		c.setSexo(console.nextLine());
		System.out.println("    =========================");
		System.out.print("    |     DATA DE NASCIMENTO: ");
		c.setDt_nas(console.nextLine());
		System.out.println("    =========================");
		System.out.print("    |     NOTA: ");
		c.setNota(Integer.parseInt(console.nextLine()));
		System.out.println("    =========================");
		AlunoDao.update(c);

		console.nextLine();
		return console;
	}

	private static Scanner excluir(Scanner console) throws ParseException {

		System.out.println("\n\n");
		System.out.println("    ###   Aluno-Excluir   ###");
		System.out.println("    =========================");
		System.out.print("    |     Digite o Id: ");
		int id = console.nextInt();
		console.nextLine();
		System.out.println("    =========================");

		AlunoDao.deleteById(id);

		console.nextLine();
		return console;
	}
}