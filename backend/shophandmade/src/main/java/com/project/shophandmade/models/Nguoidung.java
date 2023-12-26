package com.project.shophandmade.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "nguoidung")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Nguoidung extends Lopcha implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Ma")
     private Long Ma;

    @Column(name = "Hoten", length = 40)
    private String Hoten;

    @Column(name = "SDT", length = 10, nullable = false)
    private String sdt;



    @Column(name = "Diachi", length = 50)
    private String Diachi;

    @Column(name = "Matkhau", length = 64, nullable = false)
    private String matkhau;

    @Column(name = "Trangthai")
    private boolean Trangthai;

    @Column(name = "Ngaysinh")
    private Date Ngaysinh;



    @ManyToOne
    @JoinColumn(name = "Ma_Vaitro")
    private Vaitro vaitro;

    //Lay ra cac quyen nho bang vai tro
    //cover vai tro sang
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
    List<SimpleGrantedAuthority> authorityList=new ArrayList<>();
    //quyen la quyen vai tro
//    authorityList.add(new SimpleGrantedAuthority("Vaitro_"+getVaitro().getTen().toUpperCase()));
        authorityList.add(new SimpleGrantedAuthority("ROLE_"+getVaitro().getTen().toUpperCase()));
        //authorityList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
    return authorityList;
    }

    @Override
    public String getPassword() {
        return matkhau;
    }

    @Override
    public String getUsername() {
        return sdt;
    }

    //thoi luong ton tai
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //nguoi dung khong kho khoa
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
