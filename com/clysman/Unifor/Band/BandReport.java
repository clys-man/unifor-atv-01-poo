package com.clysman.Unifor.Band;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.List;
import java.util.stream.DoubleStream;

public class BandReport {
    protected BandRespository bandRespository;

    protected Comparator<Band> comparator;

    public BandReport(BandRespository bandRespository) {
        this.bandRespository = bandRespository;
    }

    public void generate() {
        // TODO: Each variable should be a private function of report
        System.out.println("--- Relatorio ---");

        int bandsWith5MembersCount = this.bandRespository.where(band -> band.members() == 5).count();
        System.out.println("Quantidade de bandas com cinco integrantes: " + bandsWith5MembersCount);

        int indieBandsCount = this.bandRespository
                .where(band -> band.type().equalsIgnoreCase("indie"))
                .count();
        System.out.println("Quantidade de bandas do tipo Indie: " + indieBandsCount);

        this.comparator = Comparator.comparingDouble(Band::gain);
        List<Band> bandsOrdered = this.bandRespository.sort(this.comparator, "desc").get();
        System.out.println("Banda com maior lucro: " + bandsOrdered.get(0).gain());
        System.out.println("Banda com menor lucro: " + bandsOrdered.get(bandsOrdered.size() - 1).gain());


        Band soloWithMostGain = this.bandRespository
                .where(band -> band.members() == 1)
                .first();
        System.out.println("Banda, que contém um único integrante, que obteve o maior lucro: " + soloWithMostGain.name());

        this.comparator = Comparator.comparingInt(Band::showsQty);
        Band bandWithLessShow = this.bandRespository
                .where(band -> band.showsQty() >= 0)
                .sort(comparator, "asc")
                .first();
        System.out.println("Banda que fez menos show: " + bandWithLessShow.name());


        int sumMembersOfAllBands = this.bandRespository
                .where(band -> band.members() > 0)
                .get().stream().mapToInt(Band::members).sum();
        System.out.println("Soma de todos os integrantes das bandas: " + sumMembersOfAllBands);

        int mostShowsBandsCount  = this.bandRespository
                .where(band -> band.members() > 1 && band.showsQty() > 33)
                .count();
        System.out.println("Bandas que possuem mais de um integrante e fizeram mais que 33 show: " + mostShowsBandsCount);

        List<Band> allBandsWithGains = this.bandRespository
                .where(band -> band.gain() > 0)
                .get();
        double sumOfGains = allBandsWithGains.stream().mapToDouble(Band::gain).sum();
        double avgOfGains = BigDecimal.valueOf(sumOfGains/allBandsWithGains.size())
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
        System.out.println("Média do lucro: R$" + avgOfGains);
    }
}
