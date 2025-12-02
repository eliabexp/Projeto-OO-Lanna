package main;

import entidades.usuario.Motorista;
import entidades.usuario.Passageiro;

import java.util.ArrayList;

public class InterfacePassageiro {
    public static void acessarPassageiro(Passageiro passageiro, ArrayList<Motorista> motoristas) {
        boolean menuAtivo = true;

        while (menuAtivo) {
            System.out.println("Olá, " + passageiro.getNome() + ", para onde você vai hoje?");
            passageiro.solicitarCorrida(motoristas);
        }
    }
}
