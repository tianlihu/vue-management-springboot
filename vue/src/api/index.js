import request from '../utils/request';

export const fetchData = query => {
    return request({
        url: './table.json',
        method: 'get',
        params: query
    });
};

export const login = params => {
    return request({
        url: '/rest/login',
        method: 'post',
        params: params
    });
};

export const logout = params => {
    return request({
        url: '/rest/logout',
        method: 'post'
    });
};
