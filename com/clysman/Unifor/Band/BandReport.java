package com.clysman.Unifor.Band;

import java.util.Comparator;

public class BandReport {
    protected BandRespository bandRespository;

    protected Comparator<Band> comparator;

    public BandReport(BandRespository bandRespository) {
        this.bandRespository = bandRespository;
    }

    public void generate() {
        System.out.println("--- Relatorio ---");

        int bandsWith5Members = this.bandRespository.where(band -> band.members() == 5).count();
        System.out.println("Quantidade de bandas com cinco integrantes: " + bandsWith5Members);

        int indieBands = this.bandRespository.where(
                band -> band.type().equalsIgnoreCase("indie")
        ).count();
        System.out.println("Quantidade de bandas do tipo Indie: " + indieBands);

        this.comparator = Comparator.comparingDouble(Band::gain);
        Band bandWithMostGain = this.bandRespository.sort(this.comparator, "desc").first();
        System.out.println("Banda com maior lucro: " + bandWithMostGain.name());

        Band bandWithMinusGain = this.bandRespository.sort(this.comparator, "asc").first();
        System.out.println("Banda com menor lucro: " + bandWithMinusGain.name());
    }
}
