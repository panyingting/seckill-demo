package reinty.study.seckill.core.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class BaseAutoIdEntity {

    private long ct;
    private long ut;
    private long ver;
    private byte del;

    private long id;

    public BaseAutoIdEntity() {
    }

    @Id
    @Column( name = "id" )
    @GeneratedValue( strategy = GenerationType.AUTO )
    public final long getId() {
        return this.id;
    }

    public final void setId(long id) {
        if (id < 0L) {
            throw new IllegalArgumentException("id must >= 0");
        } else {
            this.id = id;
        }
    }

    @Column( name = "ct" )
    public long getCt() {
        return this.ct;
    }

    public void setCt(long val) {
        this.ct = val;
    }

    @Column( name = "ut" )
    public long getUt() {
        return this.ut;
    }

    public void setUt(long val) {
        this.ut = val;
    }

    @Column( name = "ver" )
    public long getVer() {
        return this.ver;
    }

    public void setVer(long ver) {
        this.ver = ver;
    }

    @Column( name = "del" )
    public byte getDel() {
        return this.del;
    }

    public void setDel(byte val) {
        this.del = val;
    }
}
