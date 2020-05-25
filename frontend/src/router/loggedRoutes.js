import Clinics from "../pages/Clinics";
import Home from "../pages/Home";
import ClinicCenterAdministrators from "../pages/ClinicCenterAdministrators";
import ClinicAdministrators from "../pages/ClinicAdministrators";
import ClinicRooms from "../pages/ClinicRooms";
import Doctor from "../pages/Doctor";
import InterventionType from "../pages/InterventionType";
import Intervention from "../pages/Intervention";
import Nurse from "../pages/Nurse";
import Medicine from "../pages/Medicine";
import Disease from "../pages/Disease";
import UnregisteredUsers from "../pages/UnregisteredUsers";
import UserProfileView from "../components/UserProfile/UserProfileView";
import ClinicDetails from "../pages/ClinicDetails";
import Patients from "../pages/Patients";
import DoctorHome from "../pages/DoctorHome";
import WorkingSchedule from "../pages/WorkingSchedule";
import VacationRequest from "../pages/VacationRequest";
import AppointmentRequest from "../pages/AppointmentRequest";

import {
    clinicCenterAdministratorsPath,
    clinicAdministratorsPath,
    clinicDetailsPath,
    clinicRoomsPath,
    clinicsPath,
    diseasePath,
    doctorsPath,
    nursesPath,
    homePath,
    medicinesPath,
    unregisteredUsersPath,
    interventionTypePath,
    interventionPath,
    userProfilePath,
    patientsPath,
    doctorHomePath,
    vacationRequestPath,
    workingSchedulePath,
    appointmentRequestClinicPath,
    appointmentRequestPatientPath

} from "../utils/paths";


const loggedRoutes = [
    {
        path: homePath,
        component: Home,
    },
    {
        path: clinicsPath,
        component: Clinics,
    },
    {
        path: clinicCenterAdministratorsPath,
        component: ClinicCenterAdministrators,
    },
    {
        path: clinicAdministratorsPath,
        component: ClinicAdministrators,
    },

    {
        path: clinicRoomsPath,
        component: ClinicRooms,
    },

    {
        name: 'doctors',
        path: doctorsPath,
        component: Doctor,
    },

    {
        path: nursesPath,
        component: Nurse,
    },

    {
        path: medicinesPath,
        component: Medicine,
    },
    {
        path: diseasePath,
        component: Disease,
    },
    {
        path: unregisteredUsersPath,
        component: UnregisteredUsers
    },
    {
    	path: clinicDetailsPath,
    	component: ClinicDetails
    },
    {
    	path : interventionTypePath,
    	component : InterventionType
    },
    {
    	path : interventionPath,
    	component : Intervention
    },
    {
        path: userProfilePath,
        component: UserProfileView
    }
    ,
    {
        path: patientsPath,
        component: Patients
    },
    {
        path: doctorHomePath,
        component: DoctorHome
    },
    {
        path: vacationRequestPath,
        component: VacationRequest
    },
    {
        path: workingSchedulePath,
        component: WorkingSchedule
    },
    {
        path: appointmentRequestClinicPath,
        component: AppointmentRequest
    },
    ,
    {
        path: appointmentRequestPatientPath,
        component: AppointmentRequest
    }
];

export default loggedRoutes;
