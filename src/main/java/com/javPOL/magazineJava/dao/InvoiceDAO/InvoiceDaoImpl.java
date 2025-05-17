package com.javPOL.magazineJava.dao.InvoiceDAO;
import com.javPOL.magazineJava.dao.DaoImpl;
import com.javPOL.magazineJava.model.Invoice;
import org.springframework.stereotype.Repository;

@Repository
public class InvoiceDaoImpl extends DaoImpl<Invoice, Long> implements InvoiceDao {
    public InvoiceDaoImpl() {
        super(Invoice.class);
    }
}
