import { useState } from 'react';
import { useQuery, useMutation, useQueryClient } from '@tanstack/react-query';
import { DataGrid, type GridColDef, type GridCellParams } from '@mui/x-data-grid';
import Snackbar from '@mui/material/Snackbar';
import IconButton from '@mui/material/IconButton';
import DeleteIcon from '@mui/icons-material/Delete';
import { getIngredients, deleteIngredient } from '../api/ingredientapi';
import AddIngredient from '../components/AddIngredient';
import EditIngredient from '../components/EditIngredient';

function Ingredientlist() {
    const [open, setOpen] = useState(false);

    const queryClient = useQueryClient();

     const { data, error, isSuccess } = useQuery({
        queryKey: ["ingredients"],
        queryFn: getIngredients
     });

    const { mutate } = useMutation({mutationFn: deleteIngredient,
        onSuccess: () => {
            setOpen(true);
            queryClient.invalidateQueries({ queryKey: ["ingredients"] });
        },
        onError: () => {
            console.error("error");
        },
    });

    const columns: GridColDef[] = [
        { field: 'id', headerName: 'Id', width: 200 },
        { field: 'code', headerName: 'Code', width: 200 },
        { field: 'name', headerName: 'Name', width: 200 },
        { field: 'type', headerName: 'Type', width: 200 },
        {
            field: 'edit',
            headerName: '',
            width: 90,
            sortable: false,
            filterable: false,
            disableColumnMenu: true,
            renderCell: (params: GridCellParams) =>
                <EditIngredient ingredientdata={params.row} />
        },
        {
            field: 'delete', headerName: '', width: 90,
            sortable: false,
            filterable: false,
            disableColumnMenu: true,
            renderCell: (params: GridCellParams) => (
                <IconButton aria-label="delete" size="small"
                    onClick={() => {
                    if (window.confirm(`Are you sure you want to delete ${params.row.id} ${params.row.type}?`)) {
                        mutate(`${ import.meta.env.VITE_API_URL }/api/ingredients/${params.row.id}`);
                    }
                }}>
                <DeleteIcon fontSize="small"/>
                </IconButton>
            ),
        },
    ];

    if (!isSuccess) {
        return <span>Loading...</span>
    }
    else if (error) {
        return <span>Error when fetching ingredients...</span>
    }
    else {
        return (
            <>
            <AddIngredient/>
            <DataGrid
                rows={data}
                columns={columns}
                disableRowSelectionOnClick={true}
                getRowId={row => row.id}
            />
            <Snackbar
        open={open}
        autoHideDuration={2000}
        onClose={() => setOpen(false)}
        message="Car deleted" />
        </>
        );
    }
}

export default Ingredientlist;