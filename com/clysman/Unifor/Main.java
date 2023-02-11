package com.clysman.Unifor;

import com.clysman.Unifor.Band.Band;
import com.clysman.Unifor.Band.BandRespository;
import com.clysman.Unifor.Band.BandReport;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BandRespository bandRespository = new BandRespository();

        System.out.print("Informe a quantidade de banda: ");
        int bandsQty = sc.nextInt();

        for (int i = 1; i <= bandsQty; i++) {
            System.out.print("Digite o nome da banda: ");
            String name = sc.next();
            System.out.print("Digite o tipo da banda: ");
            String type = sc.next();
            System.out.print("Digite o número de integrantes: ");
            int members = sc.nextInt();
            System.out.print("Digite o lucro na última turnê: ");
            double gain = sc.nextDouble();
            System.out.print("Digite a quantidade de shows: ");
            int showsQty = sc.nextInt();

            Band band = new Band(name, type, members, gain, showsQty);
            bandRespository.add(band);
        }

        BandReport bandReport = new BandReport(bandRespository);
        bandReport.generate();
    }
}
