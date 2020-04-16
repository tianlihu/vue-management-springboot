<template>
  <div>
    <div class="crumbs">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item>
          <i class="el-icon-lx-cascades"></i> 用户管理
        </el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <div class="container">
      <div class="handle-box">
        <el-button type="primary" icon="el-icon-delete" class="handle-del mr10" @click="delAllSelection">批量删除</el-button>
        <el-select v-model="query.departmentId" placeholder="部门" class="handle-select mr10">
          <el-option label="全部" value="" />
          <el-option label="广东省" value="广东省" />
          <el-option label="湖南省" value="湖南省" />
        </el-select>
        <el-input v-model="query.name" placeholder="姓名" class="handle-input mr10" style="width:120px;" />
        <el-input v-model="query.account" placeholder="登录名" class="handle-input mr10" style="width:120px;" />
        <el-select v-model="query.admin" placeholder="是否管理员" class="handle-select mr10">
          <el-option label="全部" value="" />
          <el-option label="是" value="true" />
          <el-option label="否" value="false" />
        </el-select>
        <el-select v-model="query.status" placeholder="状态" class="handle-select mr10">
          <el-option label="全部" value="" />
          <el-option label="启用" value="true" />
          <el-option label="禁用" value="false" />
        </el-select>
        <el-button type="primary" icon="el-icon-search" @click="handleSearch">搜索</el-button>
        <el-button type="primary" icon="el-icon-circle-plus-outline" @click="handleAdd">添加</el-button>
      </div>
      <el-table :data="tableData" border class="table" ref="multipleTable" header-cell-class-name="table-header" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column prop="userId" label="编号" width="55" align="center" />
        <el-table-column prop="name" label="用户名" />
        <el-table-column prop="account" label="登录名" />
        <el-table-column prop="phone" label="电话" />
        <el-table-column label="是否管理员">
          <template slot-scope="scope">
            <el-switch v-model="scope.row.admin" />
          </template>
        </el-table-column>
        <el-table-column prop="lastLoginTime" label="最后登录时间" />
        <el-table-column prop="loginTimes" label="登录次数" />
        <el-table-column label="状态">
          <template slot-scope="scope">
            <el-switch v-model="scope.row.status" />
          </template>
        </el-table-column>
        <el-table-column label="操作" width="220" align="center">
          <template slot-scope="scope">
            <el-button type="text" icon="el-icon-edit" @click="handleRole(scope.$index, scope.row)">设置角色</el-button>
            <el-button type="text" icon="el-icon-edit" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
            <el-button type="text" icon="el-icon-delete" class="red" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <div class="pagination">
        <el-pagination @size-change="handlePageChange" @current-change="handleCurrentChange" :current-page="query.current" :total="total" :page-sizes="[5, 10, 15, 20]" :page-size="query.size" layout="total, sizes, prev, pager, next, jumper" />
      </div>
    </div>

    <!-- 添加弹出框 -->
    <el-dialog title="添加" v-dialogDrag :visible.sync="addVisible" width="30%">
      <el-form ref="form" :model="form" label-width="70px">
        <el-form-item label="部门" label-width="70px" prop="departmentId">
          <el-input v-model="form.departmentId" autocomplete="off" />
        </el-form-item>
        <el-form-item label="登录名" label-width="70px" prop="account">
          <el-input v-model="form.account" autocomplete="off" />
        </el-form-item>
        <el-form-item label="姓名" label-width="70px" prop="name">
          <el-input v-model="form.name" autocomplete="off" />
        </el-form-item>
        <el-form-item label="密码" label-width="70px" prop="password">
          <el-input v-model="form.password" autocomplete="off" />
        </el-form-item>
        <el-form-item label="电话" label-width="70px" prop="phone">
          <el-input v-model="form.phone" autocomplete="off" />
        </el-form-item>
        <el-form-item label="管理员" label-width="70px" prop="admin">
          <el-switch v-model="form.admin" />
        </el-form-item>
        <el-form-item label="状态" label-width="70px" prop="status">
          <el-switch v-model="form.status" />
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="addVisible = false">取 消</el-button>
        <el-button type="primary" @click="saveAdd">确 定</el-button>
      </span>
    </el-dialog>

    <!-- 编辑弹出框 -->
    <el-dialog title="编辑" v-dialogDrag :visible.sync="editVisible" width="30%">
      <el-form ref="form" :model="form" label-width="70px">
        <el-form-item label="部门" label-width="70px" prop="departmentId">
          <el-input v-model="form.departmentId" autocomplete="off" />
        </el-form-item>
        <el-form-item label="登录名" label-width="70px" prop="account">
          <el-input v-model="form.account" autocomplete="off" />
        </el-form-item>
        <el-form-item label="姓名" label-width="70px" prop="name">
          <el-input v-model="form.name" autocomplete="off" />
        </el-form-item>
        <el-form-item label="密码" label-width="70px" prop="password">
          <el-input v-model="form.password" autocomplete="off" />
        </el-form-item>
        <el-form-item label="电话" label-width="70px" prop="phone">
          <el-input v-model="form.phone" autocomplete="off" />
        </el-form-item>
        <el-form-item label="管理员" label-width="70px" prop="admin">
          <el-switch v-model="form.admin" />
        </el-form-item>
        <el-form-item label="状态" label-width="70px" prop="status">
          <el-switch v-model="form.status" />
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="editVisible = false">取 消</el-button>
        <el-button type="primary" @click="saveEdit">确 定</el-button>
      </span>
    </el-dialog>

    <el-dialog title="设置角色" v-dialogDrag :visible.sync="roleVisible" width="30%">
      <el-table ref="roleTable" :data="roles" tooltip-effect="dark" style="width: 100%">
        <el-table-column type="selection" width="55" />
        <el-table-column prop="name" label="名称" />
        <el-table-column prop="remark" label="备注" />
      </el-table>
      <span slot="footer" class="dialog-footer">
        <el-button @click="closeRoleDialog">取 消</el-button>
        <el-button type="primary" @click="saveRoles">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import { fetchUser, saveUser, updateUser, deleteUser, saveUserRole, fetchUserRoles } from '../../api/user';
import { fetchRoleList } from '../../api/role';
export default {
    name: 'basetable',
    data() {
        return {
            query: {
                current: 1,
                size: 10
            },
            tableData: [],
            multipleSelection: [],
            delList: [],
            addVisible: false,
            editVisible: false,
            roleVisible: false,
            total: 0,
            form: {},
            idx: -1,
            id: -1,
            roles: []
        };
    },
    created() {
        this.getData();
        this.getRoles();
    },
    methods: {
        // 获取 easy-mock 的模拟数据
        getData() {
            fetchUser(this.query).then(res => {
                this.tableData = res.records;
                this.total = res.total;
            });
        },
        getRoles() {
            fetchRoleList().then(res => {
                console.log(res);
                this.roles = res;
            });
        },
        // 触发搜索按钮
        handleSearch() {
            this.$set(this.query, 'pageIndex', 1);
            this.getData();
        },
        // 删除操作
        handleDelete(index, row) {
            // 二次确认删除
            this.$confirm('确定要删除吗？', '提示', {
                type: 'warning'
            })
                .then(() => {
                    deleteUser({ id: row.userId }).then(res => {
                        this.getData();
                        this.$message.success('删除成功');
                    });
                })
                .catch(() => {});
        },
        // 多选操作
        handleSelectionChange(val) {
            this.multipleSelection = val;
        },
        delAllSelection() {
            const length = this.multipleSelection.length;
            let ids = [];
            this.multipleSelection.forEach(row => {
                ids.push(row.userId);
            });
            deleteUser({ id: ids }).then(res => {
                this.getData();
                this.multipleSelection = [];
                this.$message.success('删除成功');
            });
        },
        // 添加操作
        handleAdd(index, row) {
            this.form = {};
            this.addVisible = true;
        },
        // 保存添加
        saveAdd() {
            saveUser(this.form).then(res => {
                this.getData();
                this.addVisible = false;
                this.$message.success('保存成功');
            });
        },
        // 编辑操作
        handleEdit(index, row) {
            this.idx = index;
            this.form = Object.assign({}, row);
            this.editVisible = true;
        },
        // 保存编辑
        saveEdit() {
            updateUser(this.form).then(res => {
                this.getData();
                this.editVisible = false;
                this.$message.success('修改成功');
            });
        },
        handleRole(index, row) {
            this.roleVisible = true;
            fetchUserRoles({ userId: row.userId }).then(res => {
                this.roles.forEach(role => {
                    if (res.data.indexOf(role.roleId) != -1) {
                        this.$refs.roleTable.toggleRowSelection(role, true);
                    }
                });

                this.form = Object.assign({}, row);
            });
        },
        saveRoles() {
            console.log(this.$refs.roleTable);
            let selection = this.$refs.roleTable.selection;
            let roleIds = [];
            selection.forEach(e => roleIds.push(e.roleId));
            saveUserRole({ userId: this.form.userId, roleIds: roleIds }).then(res => {
                this.roleVisible = false;
                this.$refs.roleTable.clearSelection();
                this.$message.success('设置角色成功');
            });
        },
        closeRoleDialog() {
            this.roleVisible = false;
            this.$refs.roleTable.clearSelection();
        },
        // 分页导航
        handleCurrentChange(val) {
            this.query.current = val;
            this.getData();
        },
        handlePageChange(val) {
            this.query.size = val;
            this.getData();
        }
    }
};
</script>

<style scoped>
.handle-box {
    margin-bottom: 20px;
}

.handle-select {
    width: 120px;
}

.handle-input {
    width: 300px;
    display: inline-block;
}
.table {
    width: 100%;
    font-size: 14px;
}
.red {
    color: #ff0000;
}
.mr10 {
    margin-right: 10px;
}
.table-td-thumb {
    display: block;
    margin: auto;
    width: 40px;
    height: 40px;
}
</style>
