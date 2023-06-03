package com.example.demo.vendedor.repository;

import com.example.demo.vendedor.model.VendedorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface SpringDataVendedorRepository extends JpaRepository<VendedorEntity, Long> {


}