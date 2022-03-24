package AddressBook;

import static AddressBook.AddressBookServiceMain.IOService.DB_IO;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;
import java.util.List;

public class AddressBookServiceMainTest {
    @Test
    public void givenAddressBookInDB_WhenRetrieved_ShouldMatchThePeopleCount() {
        AddressBookServiceMain addressBookService = new AddressBookServiceMain();
        List<AddressBookData> addressBookDataList = addressBookService.readAddressBookData(DB_IO);
        assertEquals(3,addressBookDataList.size());
    }
}