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
    Type key;                    // �ؼ���(��ֵ)
    int height;
    struct AVLTreeNode *left;    // ����
    struct AVLTreeNode *right;    // �Һ���
}Node, *AVLTree;

void preorder_avltree(AVLTree tree);
void inorder_avltree(AVLTree tree);
void postorder_avltree(AVLTree tree);
Node* avltree_minimum(AVLTree tree);
Node* avltree_maximum(AVLTree tree);
//����
Node* avltree_search(AVLTree x, Type key);

// �������뵽AVL���У����ظ��ڵ�
Node* avltree_insert(AVLTree tree, Type key);

// ɾ�����(key�ǽڵ�ֵ)�����ظ��ڵ�
Node* avltree_delete(AVLTree tree, Type key);

void adminmenu();

#endif //BINARYSORTTREE_BINARY_SORT_TREE_H
