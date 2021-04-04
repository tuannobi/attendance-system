package com.ty.attendancesystem.service;

import com.ty.attendancesystem.base.BaseService;
import com.ty.attendancesystem.model.Class;

public interface ClassService extends BaseService<Class, String> {
    Class insert(Class course);
    Class update(Class course);
}
