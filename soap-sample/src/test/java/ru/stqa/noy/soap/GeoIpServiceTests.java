package ru.stqa.noy.soap;

import net.webservicex.GeoIP;
import net.webservicex.GeoIPService;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class GeoIpServiceTests {

  @Test
  public void testMyIp() {
    GeoIP geoIP = new GeoIPService().getGeoIPServiceSoap12().getGeoIP("77.247.169.20");
    assertEquals(geoIP.getCountryCode(), "RUS");
  }

  /* @Test
  public void testInvalidIp() {
    GeoIP geoIP = new GeoIPService().getGeoIPServiceSoap12().getGeoIP("77.247.169.xxx");
    assertEquals(geoIP.getCountryCode(), "RUS");
  } */
}
