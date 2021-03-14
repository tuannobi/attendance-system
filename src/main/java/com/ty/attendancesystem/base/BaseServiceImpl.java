package com.ty.attendancesystem.base;

import jdk.nashorn.internal.runtime.options.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public abstract class BaseServiceImpl<T,ID> implements BaseService<T,ID>{

  protected abstract JpaRepository<T,ID> getRepository();

  @Transactional(readOnly = true)
  @Override
  public List<T> findAll() {
    return getRepository().findAll();
  }

  @Transactional(readOnly = true)
  @Override
  public Optional<T> findById(ID id) {
    return getRepository().findById(id);
  }

  @Transactional(readOnly = true)
  @Override
  public Boolean existById(ID id) {
    return getRepository().existsById(id);
  }

  @Override
  public T save(T t) {
    return getRepository().save(t);
  }

  @Transactional(readOnly = true)
  @Override
  public long count() {
    return getRepository().count();
  }

  @Override
  public void deleteById(ID id) {
    getRepository().deleteById(id);
  }

  @Override
  public void delete(T t) {
    getRepository().delete(t);
  }
}
