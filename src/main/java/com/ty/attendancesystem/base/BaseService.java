package com.ty.attendancesystem.base;

import java.util.List;
import java.util.Optional;

public interface BaseService <T,ID> {
  List<T> findAll();
  Optional<T> findById(ID id);
  Boolean existById(ID id);
  T save(T t);
  long count();
  void deleteById(ID id);
  void delete(T t);
}
