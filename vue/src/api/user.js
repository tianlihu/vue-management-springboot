import request from '../utils/request';

export const fetchData = query => {
    return request({
        url: '/rest/user/page',
        method: 'post',
        data: query
    });
};

export const saveData = data => {
    return request({
        url: '/rest/user/save',
        method: 'post',
        data: data
    });
};

export const updateData = data => {
    return request({
        url: '/rest/user/update',
        method: 'post',
        data: data
    });
};

export const deleteData = params => {
    return request({
        url: '/rest/user/delete',
        method: 'get',
        params: params
    });
};
