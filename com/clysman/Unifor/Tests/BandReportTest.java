package com.clysman.Unifor.Tests;

import com.clysman.Unifor.Band.Band;
import com.clysman.Unifor.Band.BandReport;
import com.clysman.Unifor.Band.BandRespository;

public class BandReportTest {
    public static void main(String[] args) {
        BandRespository bandRespository = new BandRespository();
        bandRespository.add(new Band("Los Hermanos", "Indie", 4, 58000, 31));
        bandRespository.add(new Band("Ed Sheeran", "Folk", 1, 47000, 29));
        bandRespository.add(new Band("Lenine", "MPB", 1, 32000, 15));
        bandRespository.add(new Band("Oasis", "Rock", 5, 62000, 45));
        bandRespository.add(new Band("Rubel", "Folk", 1, 33000, 20));
        bandRespository.add(new Band("Aerosmith", "Rock", 5, 42000, 34));

        BandReport bandReport = new BandReport(bandRespository);
        bandReport.generate();
    }
}
