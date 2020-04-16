import request from '../utils/request';

export const fetchRoleList = () => {
    return request({
        url: '/rest/role/list',
        method: 'post',
        data: {}
    });
};

export const fetchRole = query => {
    return request({
        url: '/rest/role/page',
        method: 'post',
        data: query
    });
};

export const saveRole = data => {
    return request({
        url: '/rest/role/save',
        method: 'post',
        data: data
    });
};

export const updateRole = data => {
    return request({
        url: '/rest/role/update',
        method: 'post',
        data: data
    });
};

export const deleteRole = params => {
    return request({
        url: '/rest/role/delete',
        method: 'get',
        params: params
    });
};
