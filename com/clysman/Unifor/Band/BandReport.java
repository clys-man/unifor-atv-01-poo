package com.clysman.Unifor.Band;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class BandReport {
    protected BandRespository bandRespository;

    protected Comparator<Band> comparator;

    public BandReport(BandRespository bandRespository) {
        this.bandRespository = bandRespository;
    }

    public void generate() {
        System.out.println("--- Relatorio ---");

        int bandsWith5Members = this.bandRespository.where(band -> band.getMembers() == 5).count();
        System.out.println("Quantidade de bandas com cinco integrantes: " + bandsWith5Members);

        int indieBands = this.bandRespository.where(
                band -> band.getType().equalsIgnoreCase("indie")
        ).count();
        System.out.println("Quantidade de bandas do tipo Indie: " + indieBands);

        this.comparator = Comparator.comparingDouble(Band::getGain);
        Band bandWithMostGain = this.bandRespository.sort(this.comparator, "desc").first();
        System.out.println("Banda com maior lucro: " + bandWithMostGain.getName());

        Band bandWithMinusGain = this.bandRespository.sort(this.comparator, "asc").first();
        System.out.println("Banda com menor lucro: " + bandWithMinusGain.getName());
    }
}
