package domain;

import data.Context;
import data.Purchase;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class PurchaseFacadeTest {

    @Test
    public void testCancelled() {
        Purchase purchase = Purchase.builder()
                .id("12345678")
                .status("cancelled")
                .statusDetail("mediation")
                .tags(Collections.singletonList("dummy_tag"))
                .currencyId("ARS")
                .safetyKeyword("")
                .context(
                        Context.builder()
                                .site("MLA")
                                .flows(Collections.emptyList())
                                .build()
                )
                .build();

        PurchaseFacade purchaseFacade = new PurchaseFacade(purchase);

        assertTrue(purchaseFacade.isPurchaseCancelled());
    }

    @Test
    public void testNoCancelled() {
        Purchase purchase = Purchase.builder()
                .id("12345678")
                .status("paid")
                .statusDetail("")
                .tags(Collections.singletonList("dummy_tag"))
                .currencyId("ARS")
                .safetyKeyword("")
                .context(
                        Context.builder()
                                .site("MLA")
                                .flows(Collections.emptyList())
                                .build()
                )
                .build();

        PurchaseFacade purchaseFacade = new PurchaseFacade(purchase);

        assertFalse(purchaseFacade.isPurchaseCancelled());
    }

    @Test
    public void testReturnFirstTag() {
        Purchase purchase = Purchase.builder()
                .id("12345678")
                .status("paid")
                .statusDetail("")
                .tags(Arrays.asList("dummy_tag", "dummy_tag_2"))
                .currencyId("ARS")
                .safetyKeyword("")
                .context(
                        Context.builder()
                                .site("MLA")
                                .flows(Collections.emptyList())
                                .build()
                )
                .build();

        PurchaseFacade purchaseFacade = new PurchaseFacade(purchase);

        boolean emptyTags = purchaseFacade.hasEmptyTags();

        String expectedFirstTag;

        if(emptyTags) {
            expectedFirstTag = "";
        } else {
            expectedFirstTag = "dummy_tag";
        }

        String firstTag = purchaseFacade.getFirstTag();

        assertEquals(expectedFirstTag, firstTag);
        assertNotEquals("dummy_tag_2", firstTag);
    }

    @Test
    public void validateDateTest() {
        Date date = new Date(124, Calendar.JUNE, 00, 00, 00);

        LocalDate localDate = date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        Purchase purchase = Purchase.builder()
                .id("12345678")
                .status("paid")
                .statusDetail("")
                .tags(Collections.singletonList("dummy_tag"))
                .date(localDate)
                .currencyId("ARS")
                .safetyKeyword("")
                .context(
                        Context.builder()
                                .site("MLA")
                                .flows(Collections.emptyList())
                                .build()
                )
                .build();

        PurchaseFacade purchaseFacade = new PurchaseFacade(purchase);

        String localDateAsString = purchaseFacade.getDateAsString();

        assertEquals("2024-05-31", localDateAsString);
    }

}