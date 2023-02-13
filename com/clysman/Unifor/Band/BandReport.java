package com.clysman.Unifor.Band;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class BandReport {
    protected BandRespository bandRespository;

    public BandReport(BandRespository bandRespository) {
        this.bandRespository = bandRespository;
    }

    public void generate() {
        System.out.println("--- Relatorio ---");

        int bandsWith5Members = this.countBandsWhere(band -> band.members() == 5);
        System.out.println("Quantidade de bandas com cinco integrantes: " + bandsWith5Members);

        int indieBands = this.countBandsWhere(band -> band.type().equalsIgnoreCase("indie"));
        System.out.println("Quantidade de bandas do tipo Indie: " + indieBands);

        List<Band> bandsOrderedByGains = getBandsOrdered();
        System.out.println("Banda com maior lucro: " + bandsOrderedByGains.get(0).gain());
        System.out.println("Banda com menor lucro: " + bandsOrderedByGains.get(bandsOrderedByGains.size() - 1).gain());

        Band soloWithMostGain = bandsOrderedByGains.stream().filter(band -> band.members() == 1).findFirst().get();
        System.out.println("Banda, que contém um único integrante, que obteve o maior lucro: " + soloWithMostGain.name());

        Band bandWithLessShow = getBandWithLessShow();
        System.out.println("Banda que fez menos show: " + bandWithLessShow.name());

        int bandMembersSum = sumMembersOfAllBands();
        System.out.println("Soma de todos os integrantes das bandas: " + bandMembersSum);

        int mostShowsBandCount = countBandsWhere(band -> band.members() > 1 && band.showsQty() > 33);
        System.out.println("Bandas que possuem mais de um integrante e fizeram mais que 33 show: " + mostShowsBandCount);

        double bandGainAvg = avgOfGains();
        System.out.println("Média do lucro: R$" + bandGainAvg);
    }

    private int countBandsWhere(Predicate<? super Band> condition) {
        return this.bandRespository.where(condition).count();
    }

    private List<Band> getBandsWhere(Predicate<? super Band> condition) {
        return this.bandRespository.where(condition).get();
    }

    private List<Band> getBandsOrdered() {
        Comparator<Band> comparator = Comparator.comparingDouble(Band::gain);

        return this.bandRespository.sort(comparator, "desc").get();
    }

    private Band getSoloWithMostGain() {
        Comparator<Band> comparator = Comparator.comparingDouble(Band::gain);

        return this.bandRespository
                .where(band -> band.members() == 1)
                .sort(comparator, "desc")
                .first();
    }

    private Band getBandWithLessShow() {
        Comparator<Band> comparator = Comparator.comparingInt(Band::showsQty);

        return this.bandRespository
                .where(band -> band.showsQty() >= 0)
                .sort(comparator, "asc")
                .first();
    }

    private int sumMembersOfAllBands() {
        return this.getBandsWhere(band -> band.members() >= 0)
                .stream()
                .mapToInt(Band::members)
                .sum();
    }

    private double avgOfGains() {
        List<Band> bandsWithgains = this.getBandsWhere(band -> band.gain() > 0);
        double sumOfGains = bandsWithgains.stream().mapToDouble(Band::gain).sum();

        return BigDecimal.valueOf(sumOfGains/bandsWithgains.size())
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
    }
}
