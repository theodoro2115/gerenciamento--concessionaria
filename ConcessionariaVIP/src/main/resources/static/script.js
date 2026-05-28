function showSection(id) {
    document.querySelectorAll('.content-section').forEach(s => s.style.display = 'none');
    document.getElementById(id).style.display = 'block';
}


showSection('cadastrar-veiculo');


document.getElementById('form-veiculo').onsubmit = async (e) => {
    e.preventDefault();
    const data = {
        modelo: document.getElementById('v-modelo').value,
        fabricante: document.getElementById('v-fabricante').value,
        ano: document.getElementById('v-ano').value,
        tipo: document.getElementById('v-tipo').value
    };
    
    const res = await fetch('/api/veiculos', {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(data)
    });
    
    const result = await res.json();
    if (res.ok) {
        alert(result.message);
        e.target.reset();
    } else {
        alert("Erro: " + result.error);
    }
};


document.getElementById('form-cliente').onsubmit = async (e) => {
    e.preventDefault();
    const data = {
        nome: document.getElementById('c-nome').value,
        contato: document.getElementById('c-contato').value,
        documento: document.getElementById('c-documento').value,
        tipo: document.getElementById('c-tipo').value
    };
    
    const res = await fetch('/api/clientes', {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(data)
    });
    
    const result = await res.json();
    if (res.ok) {
        alert(result.message);
        e.target.reset();
    } else {
        alert("Erro: " + result.error);
    }
};

async function loadVeiculos() {
    const res = await fetch('/api/veiculos');
    const data = await res.json();
    const container = document.getElementById('lista-veiculos');
    container.innerHTML = '';
    
    data.forEach(v => {
        const div = document.createElement('div');
        div.className = `card ${v.status === 'vendido' ? 'vendido' : ''}`;
        div.innerHTML = `
            <div>
                <strong>${v.info}</strong><br>
                <small>${v.status === 'vendido' ? 'Comprado por: ' + v.comprador + ' em ' + v.data_venda : 'Disponível'}</small>
            </div>
            <button class="btn-delete" onclick="deletarVeiculo(${v.id})">X</button>
        `;
        container.appendChild(div);
    });
}

async function loadClientes() {
    const res = await fetch('/api/clientes');
    const data = await res.json();
    const container = document.getElementById('lista-clientes');
    container.innerHTML = '';
    
    data.forEach(c => {
        const div = document.createElement('div');
        div.className = 'card';
        div.innerHTML = `
            <div><strong>${c.info}</strong></div>
            <button class="btn-delete" onclick="deletarCliente(${c.id})">X</button>
        `;
        container.appendChild(div);
    });
}

async function deletarVeiculo(id) {
    if (confirm("Deseja remover este veículo?")) {
        await fetch(`/api/veiculos/${id}`, { method: 'DELETE' });
        loadVeiculos();
    }
}

async function deletarCliente(id) {
    if (confirm("Deseja remover este cliente?")) {
        await fetch(`/api/clientes/${id}`, { method: 'DELETE' });
        loadClientes();
    }
}

async function loadVendaOptions() {
    const resV = await fetch('/api/veiculos');
    const veiculos = await resV.json();
    const resC = await fetch('/api/clientes');
    const clientes = await resC.json();
    
    const selV = document.getElementById('select-veiculo');
    const selC = document.getElementById('select-cliente');
    
    selV.innerHTML = '<option value="">Selecione o Veículo</option>';
    veiculos.filter(v => v.status === 'disponível').forEach(v => {
        selV.innerHTML += `<option value="${v.id}">${v.modelo} (${v.fabricante})</option>`;
    });
    
    selC.innerHTML = '<option value="">Selecione o Cliente</option>';
    clientes.forEach(c => {
        selC.innerHTML += `<option value="${c.id}">${c.nome}</option>`;
    });
}

async function efetuarVenda() {
    const vId = document.getElementById('select-veiculo').value;
    const cId = document.getElementById('select-cliente').value;
    
    if (!vId || !cId) return alert("Selecione veículo e cliente!");
    
    const res = await fetch('/api/vendas', {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify({ veiculo_id: parseInt(vId), cliente_id: parseInt(cId) })
    });
    
    const result = await res.json();
    if (res.ok) {
        alert("Venda realizada!");
        showSection('historico-vendas');
        loadHistorico();
    } else {
        alert("Erro: " + result.error);
    }
}

async function loadHistorico() {
    const res = await fetch('/api/vendas');
    const data = await res.json();
    const tbody = document.getElementById('corpo-vendas');
    tbody.innerHTML = '';
    
    data.forEach(v => {
        tbody.innerHTML += `<tr><td>${v.veiculo}</td><td>${v.cliente}</td><td>${v.data}</td></tr>`;
    });
}
