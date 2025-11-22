package usuario;

import corrida.Corrida;
import corrida.Local;
import corrida.Rota;
import pagamento.FormasDePagamento;

import java.util.ArrayList;
import java.util.Scanner;

public class Passageiro extends Usuario {
    private ArrayList<FormasDePagamento> formasdepagamento = new ArrayList<FormasDePagamento>();
    private float saldo;

    public Passageiro(String nome, String email, String cpf, String numeroDeTelefone, String senha) {
        super(nome, email, cpf, numeroDeTelefone, senha);
    }

    public static Passageiro cadastrarPassageiro(Scanner sc) {
        System.out.println("---Cadastro---");
        System.out.println("Nome:");
        String nome = sc.nextLine();
        System.out.println("Email:");
        String email = sc.nextLine();
        System.out.println("CPF [999.999.999-99]:");
        String cpf = sc.nextLine();
        System.out.println("Numero de Telefone [(99) 99999-9999]");
        String numeroDeTelefone = sc.nextLine();
        System.out.println("Senha:");
        String senhaHash = sc.nextLine();

        return new Passageiro(nome, email, cpf, numeroDeTelefone, senhaHash);
    }

    public void solicitarCorrida(Scanner sc) {
        System.out.println("Local de partida: (nome, latitude, longitude)");
        Local partida = Local.inserirLocal(sc);
        System.out.println("Local de destino: (nome, latitude, longitude)");
        Local destino = Local.inserirLocal(sc);
        System.out.println("Escolha o tipo da viagem: \n1: Luxo\n2: Comfort\n3: Comum\n4: Moto");
        TipoVeiculo tipoVeiculo = Veiculo.mapTipoVeiculo(sc.nextInt());

        Rota rota = new Rota(partida, destino);

        Corrida corrida = new Corrida(rota, this, tipoVeiculo);

        System.out.println("Deseja confirmar a viagem? (s|n):");
        String resposta = sc.nextLine();

        if (!resposta.equalsIgnoreCase("s")) corrida.cancelar();
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }
}
