package bla.konishy.stripsleeve;

import bla.konishy.stripsleeve.data.Identifier;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.UUID;

public class CokeTest {

    @Test
    public void testCrud() {
        SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();

        Identifier md = new Identifier("DSC_" + UUID.randomUUID(), "path/2/" + UUID.randomUUID());

        create(session, md);
        List all = read(session);
        for (Object o : all) {
            if (((Identifier) o).getId() == md.getId()) {
                Assert.assertEquals(o, md);
            }
        }

        Identifier md3 = (Identifier) session.get(Identifier.class, md.getId());
        md3.setName("newname");

        all = read(session);
        for (Object o : all) {
            delete(session, (Identifier)o);
        }

        System.out.println("--");
        Assert.assertEquals(0, read(session).size());
    }

    private Identifier create(Session s, Identifier md) {
        s.beginTransaction();
        s.save(md);
        s.getTransaction().commit();
        return md;
    }

    private void delete(Session session, Identifier md) {
        session.beginTransaction();
        session.delete(md);
        session.getTransaction().commit();
    }

    private void update(Session session, Identifier md, Identifier newMd) {
        newMd.setId(md.getId());
        session.beginTransaction();
        session.saveOrUpdate(newMd);
        session.getTransaction().commit();
    }


    private List read(Session s) {
        Query q = s.createQuery("select _metadata from Identifier _metadata");
        return q.list();
    }


}