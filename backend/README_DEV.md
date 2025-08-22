# 📁 Estrutura Lógica do Backend - WorkZone

Este projeto segue uma estrutura organizada em pacotes com prefixos alfabéticos para facilitar o entendimento do fluxo dinâmico da aplicação.

---

## 🔁 Ordem lógica do fluxo

```txt
a_controller → b_service → c_repository → d_model
                                 ↓
                              e_dto ↔ f_mapper
