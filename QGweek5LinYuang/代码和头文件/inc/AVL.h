//
// Created by eke_l on 2021/4/21.
//

#ifndef BINARYSORTTREE_BINARY_SORT_TREE_H
#define BINARYSORTTREE_BINARY_SORT_TREE_H
#define true 1
#define false 0
#define succeed 1
#define failed 0
#define Status int
typedef int ElemType;
typedef int Type;

//AVL
typedef struct AVLTreeNode{
    Type key;                    // 关键字(键值)
    int height;
    struct AVLTreeNode *left;    // 左孩子
    struct AVLTreeNode *right;    // 右孩子
}Node, *AVLTree;

void preorder_avltree(AVLTree tree);
void inorder_avltree(AVLTree tree);
void postorder_avltree(AVLTree tree);
Node* avltree_minimum(AVLTree tree);
Node* avltree_maximum(AVLTree tree);
//查找
Node* avltree_search(AVLTree x, Type key);

// 将结点插入到AVL树中，返回根节点
Node* avltree_insert(AVLTree tree, Type key);

// 删除结点(key是节点值)，返回根节点
Node* avltree_delete(AVLTree tree, Type key);

void adminmenu();

#endif //BINARYSORTTREE_BINARY_SORT_TREE_H
