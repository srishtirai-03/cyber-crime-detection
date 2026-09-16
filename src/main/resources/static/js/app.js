document.addEventListener('DOMContentLoaded', () => {
    const navBtns = document.querySelectorAll('.nav-btn');
    const views = document.querySelectorAll('.view');
    
    // Navigation
    navBtns.forEach(btn => {
        btn.addEventListener('click', () => {
            navBtns.forEach(b => b.classList.remove('active'));
            views.forEach(v => v.classList.remove('active'));
            
            btn.classList.add('active');
            document.getElementById(btn.dataset.target).classList.add('active');
            
            if (btn.dataset.target === 'dashboard-view') {
                loadDashboardStats();
            }
        });
    });

    // Initial load
    loadDashboardStats();

    // Register Form
    document.getElementById('register-form').addEventListener('submit', async (e) => {
        e.preventDefault();
        
        const complaintData = {
            complainantName: document.getElementById('complainantName').value,
            contactNumber: document.getElementById('contactNumber').value,
            crimeType: document.getElementById('crimeType').value,
            priority: document.getElementById('priority').value,
            financialLoss: document.getElementById('financialLoss').value,
            description: document.getElementById('description').value
        };

        try {
            const res = await fetch('/api/complaints', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(complaintData)
            });
            const data = await res.json();
            
            const msgEl = document.getElementById('register-message');
            msgEl.innerHTML = `<p style="color: #10b981; margin-top: 1rem;">Successfully registered! ID: ${data.generatedId}</p>`;
            document.getElementById('register-form').reset();
            
            setTimeout(() => { msgEl.innerHTML = ''; }, 5000);
        } catch (err) {
            console.error(err);
            alert('Failed to register complaint.');
        }
    });

    // Search
    document.getElementById('search-btn').addEventListener('click', async () => {
        const searchId = document.getElementById('searchId').value.trim();
        if (!searchId) return;

        try {
            const res = await fetch(`/api/complaints/${searchId}`);
            if (!res.ok) {
                alert('Complaint not found!');
                document.getElementById('search-result').classList.add('hidden');
                return;
            }
            
            const data = await res.json();
            document.getElementById('search-result').classList.remove('hidden');
            
            document.getElementById('res-id').innerText = `Complaint Details: ${data.generatedId}`;
            document.getElementById('res-name').innerText = data.complainantName;
            document.getElementById('res-contact').innerText = data.contactNumber;
            document.getElementById('res-type').innerText = data.crimeType;
            document.getElementById('res-loss').innerText = data.financialLoss;
            document.getElementById('res-priority').innerText = data.priority;
            document.getElementById('res-status').innerText = data.status;
            
            document.getElementById('updateStatus').value = data.status;
            
            // Badge Colors
            const priorityBadge = document.getElementById('res-priority');
            priorityBadge.style.backgroundColor = getBadgeColor(data.priority);
            
            const statusBadge = document.getElementById('res-status');
            statusBadge.style.backgroundColor = getBadgeColor(data.status === 'RESOLVED' || data.status === 'CLOSED' ? 'LOW' : 'HIGH');
            
        } catch (err) {
            console.error(err);
        }
    });

    // Update Status
    document.getElementById('update-btn').addEventListener('click', async () => {
        const searchId = document.getElementById('searchId').value.trim();
        const newStatus = document.getElementById('updateStatus').value;
        
        try {
            const res = await fetch(`/api/complaints/${searchId}/status`, {
                method: 'PUT',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ status: newStatus })
            });
            
            if (res.ok) {
                const data = await res.json();
                document.getElementById('res-status').innerText = data.status;
                const msgEl = document.getElementById('update-message');
                msgEl.innerHTML = `<p style="color: #10b981; margin-top: 1rem;">Status updated successfully!</p>`;
                setTimeout(() => { msgEl.innerHTML = ''; }, 3000);
            }
        } catch (err) {
            console.error(err);
        }
    });

    async function loadDashboardStats() {
        try {
            const res = await fetch('/api/complaints/report');
            const stats = await res.json();
            
            document.getElementById('stat-total').innerText = stats.totalComplaints;
            document.getElementById('stat-open').innerText = stats.openComplaints;
            document.getElementById('stat-resolved').innerText = stats.resolvedComplaints;
            document.getElementById('stat-high').innerText = stats.highPriorityCases;
            document.getElementById('stat-most-category').innerText = stats.mostReportedCategory;
            
        } catch (err) {
            console.error('Failed to load stats', err);
        }
    }
    
    function getBadgeColor(val) {
        if (val === 'HIGH') return 'var(--badge-high)';
        if (val === 'MEDIUM') return 'var(--badge-med)';
        if (val === 'LOW') return 'var(--badge-low)';
        return 'var(--accent)';
    }
});
